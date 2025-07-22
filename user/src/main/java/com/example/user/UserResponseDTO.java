package com.example.user;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isKycVerified() {
        return isKycVerified;
    }

    public void setKycVerified(boolean kycVerified) {
        isKycVerified = kycVerified;
    }

    private boolean isKycVerified;
}
