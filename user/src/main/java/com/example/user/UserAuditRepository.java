package com.example.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserAuditRepository extends MongoRepository<UserAudit, String> {

}