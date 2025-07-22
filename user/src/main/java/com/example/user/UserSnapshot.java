package com.example.user;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserSnapshot {
    private Long id;
    private Long version;
    private String username;
    private String email;
    private String phoneNumber;
    private String address;
    private String country;
    private String ssn;
    private boolean isKycVerified;
    private String accountType;
    private LocalDate dateOfBirth;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserSnapshot(User user) {
        this.id = user.getId();
        this.version = user.getVersion();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.address = user.getAddress();
        this.country = user.getCountry();
        this.ssn = user.getSsn();
        this.isKycVerified = user.isKycVerified();
        this.accountType = user.getAccountType();
        this.dateOfBirth = user.getDateOfBirth();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }

    public UserSnapshot() {
    }

    public void setIsKycVerified(boolean b) {
    }

    public void setPhoneno(long l) {
    }
}
