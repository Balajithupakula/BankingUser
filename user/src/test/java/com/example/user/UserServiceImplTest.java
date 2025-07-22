package com.example.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserAuditRepository auditRepository;

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Mock
    private org.springframework.data.mongodb.core.MongoTemplate mongoTemplate;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private UserRequestDTO sampleDto() {
        UserRequestDTO dto = new UserRequestDTO();
        dto.setUsername("john");
        dto.setEmail("john@example.com");
        dto.setPhoneNumber("1234567890");
        dto.setAddress("USA");
        dto.setCountry("US");
        dto.setSsn("111-22-3333");
        dto.setAccountType("SAVINGS");
        dto.setKycVerified(true);
        dto.setDateOfBirth(LocalDate.parse("1990-01-01"));
        return dto;
    }

    @Test
    void testCreateUser_success() {
        UserRequestDTO dto = sampleDto();
        User user = new UserMapper().toEntity(dto);
        user.setId(1L);

        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponseDTO response = userService.createUser(dto);

        assertNotNull(response);
        assertEquals("john", response.getUsername());
        assertTrue(response.isKycVerified());

        verify(userRepository).save(any(User.class));
        verify(kafkaTemplate).send(eq("user-topic"), any(User.class));
        verify(auditRepository).save(any(UserAudit.class));
    }

    @Test
    void testUpdateUser_userNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        UserRequestDTO dto = sampleDto();

        assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(99L, dto));
    }

    @Test
    void testUpdateUser_success() {
        UserRequestDTO dto = sampleDto();
        User existing = new UserMapper().toEntity(dto);
        existing.setId(2L);

        when(userRepository.findById(2L)).thenReturn(Optional.of(existing));
        when(userRepository.save(any(User.class))).thenReturn(existing);

        UserResponseDTO response = userService.updateUser(2L, dto);

        assertNotNull(response);
        assertEquals("john", response.getUsername());

        verify(userRepository).save(any(User.class));
        verify(kafkaTemplate).send(eq("user-topic"), any(User.class));
        verify(auditRepository).save(any(UserAudit.class));
    }

    @Test
    void testGetUser_userNotFound() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.getUser(999L));
    }

    @Test
    void testGetUser_success() {
        User user = new User();
        user.setId(3L);
        user.setUsername("alice");
        user.setEmail("alice@example.com");

        when(userRepository.findById(3L)).thenReturn(Optional.of(user));

        UserResponseDTO response = userService.getUser(3L);

        assertEquals("alice", response.getUsername());
    }

    @Test
    void testDeleteUser_success() {
        User user = new User();
        user.setId(5L);
        when(userRepository.findById(5L)).thenReturn(Optional.of(user));

        userService.deleteUser(5L);

        verify(userRepository).delete(user);
        verify(kafkaTemplate).send(eq("user-topic"), any(User.class));
        verify(auditRepository).save(any(UserAudit.class));
    }
}
