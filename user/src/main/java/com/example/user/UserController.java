package com.example.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserRequestDTO dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long userId, @RequestBody UserRequestDTO dto) {
        return ResponseEntity.ok(userService.updateUser(userId, dto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> get(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
