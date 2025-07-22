package com.example.user;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Document(collection = "user_audit")
@Data
public class UserAudit {
    @Id
    private String id;

    private Long userId;
    private String action;
    private UserSnapshot oldData;  // ✅ change from User to UserSnapshot
    private UserSnapshot newData;  // ✅ change from User to UserSnapshot
    private LocalDateTime timestamp;
public UserAudit(){

}
    public UserAudit(String id, Long userId, String action, UserSnapshot oldData, UserSnapshot newData, LocalDateTime timestamp) {
        this.id = id;
        this.userId = userId;
        this.action = action;
        this.oldData = oldData;
        this.newData = newData;
        this.timestamp = timestamp;
    }
}