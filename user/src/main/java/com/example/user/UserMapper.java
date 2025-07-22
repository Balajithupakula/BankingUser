package com.example.user;
public class UserMapper {

    public User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setAddress(dto.getAddress());
        user.setCountry(dto.getCountry());
        user.setSsn(dto.getSsn());
        user.setKycVerified(dto.isKycVerified());
        user.setAccountType(dto.getAccountType());
        user.setDateOfBirth(dto.getDateOfBirth());
        return user;
    }

    public UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setKycVerified(user.isKycVerified());
        return dto;
    }
}
