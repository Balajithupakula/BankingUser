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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    private String username;
    private String email;

    public User(User existing) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

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
                ", ssn='" + ssn + '\'' +
                ", isKycVerified=" + isKycVerified +
                ", accountType='" + accountType + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
    public User(){

    }
    public User(Long id, Long version, String username, String email, String phoneNumber, String address, String country, String ssn, boolean isKycVerified, String accountType, LocalDate dateOfBirth, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.version = version;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.country = country;
        this.ssn = ssn;
        this.isKycVerified = isKycVerified;
        this.accountType = accountType;
        this.dateOfBirth = dateOfBirth;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public boolean isKycVerified() {
        return isKycVerified;
    }

    public void setKycVerified(boolean kycVerified) {
        isKycVerified = kycVerified;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

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
}