package com.example.user;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO dto);
    UserResponseDTO updateUser(Long userId, UserRequestDTO dto);
    UserResponseDTO getUser(Long userId);
    void deleteUser(Long userId);
}
