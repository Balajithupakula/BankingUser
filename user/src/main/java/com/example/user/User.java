package com.example.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
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

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // ✅ Proper copy constructor
    public User(User existing) {
        this.id = existing.id;
        this.version = existing.version;
        this.username = existing.username;
        this.email = existing.email;
        this.phoneNumber = existing.phoneNumber;
        this.address = existing.address;
        this.country = existing.country;
        this.ssn = existing.ssn;
        this.isKycVerified = existing.isKycVerified;
        this.accountType = existing.accountType;
        this.dateOfBirth = existing.dateOfBirth;
        this.createdAt = existing.createdAt;
        this.updatedAt = existing.updatedAt;
    }

    // ✅ Redacted SSN in toString for security
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", version=" + version +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", ssn='***'" +
                ", isKycVerified=" + isKycVerified +
                ", accountType='" + accountType + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
