package com.example.user;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserAuditRepository auditRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final MongoTemplate mongoTemplate;

    @Transactional
    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {
        log.info("Creating user: {}", dto.getUsername());
        User user = new UserMapper().toEntity(dto);
        User savedUser = userRepository.save(user);

        // Save audit and publish to Kafka
        saveAudit(null, savedUser, "CREATE");
        kafkaTemplate.send("user-topic", savedUser);

        return new UserMapper().toResponseDTO(savedUser);
    }

    @Transactional
    @Override
    public UserResponseDTO updateUser(Long userId, UserRequestDTO dto) {
        User existing = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        log.info("Updating user with ID: {}", userId);
        User oldCopy = new User(existing); // deep copy for audit

        // Update fields
        existing.setAddress(dto.getAddress());
        existing.setKycVerified(dto.isKycVerified());
        existing.setPhoneNumber(dto.getPhoneNumber());
        // Add other field updates here...

        User updated = userRepository.save(existing);

        // Save audit and publish to Kafka
        saveAudit(oldCopy, updated, "UPDATE");
        kafkaTemplate.send("user-topic", updated);

        return new UserMapper().toResponseDTO(updated);
    }

    @Override
    public UserResponseDTO getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return new UserMapper().toResponseDTO(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        userRepository.delete(user);
        saveAudit(user, null, "DELETE");
        kafkaTemplate.send("user-topic", user);
        log.info("User with ID {} deleted", userId);
    }

    private void saveAudit(User oldData, User newData, String action) {
        try {
            UserAudit audit = new UserAudit();
            audit.setUserId(newData != null ? newData.getId() : oldData.getId());
            audit.setAction(action);
            audit.setOldData(oldData != null ? new UserSnapshot(oldData) : null);
            audit.setNewData(newData != null ? new UserSnapshot(newData) : null);
            audit.setTimestamp(LocalDateTime.now());

            auditRepository.save(audit);
            log.info("Audit log saved for userId: {}", audit.getUserId());
        } catch (Exception e) {
            log.error(" Failed to save audit log to MongoDB: {}", e.getMessage(), e);
        }
    }

    /**
     * Test method to verify MongoDB insert - should be removed/commented in prod
     */
    @PostConstruct
    public void testMongoInsert() {
        UserSnapshot snapshot = new UserSnapshot();
        snapshot.setUsername("testUser");
        snapshot.setEmail("test@example.com");
        snapshot.setPhoneno(1234567890L);
        snapshot.setAddress("Test Address");
        snapshot.setCountry("Test Country");
        snapshot.setSsn("123-45-6789");
        snapshot.setAccountType("SAVINGS");
        snapshot.setIsKycVerified(true);
        snapshot.setDateOfBirth(LocalDate.parse("2000-01-01"));

        UserAudit audit = new UserAudit();
        audit.setUserId(999L);
        audit.setAction("TEST_INSERT");
        audit.setOldData(null);
        audit.setNewData(snapshot);
        audit.setTimestamp(LocalDateTime.now());

        mongoTemplate.save(audit);
        log.info("✅ Test document inserted in MongoDB.");
    }
}
