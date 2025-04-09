package com.jobportal.repository;

import com.jobportal.entity.Otp;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OtpRepository extends MongoRepository<Otp, String> {
    List<Otp> findByCreatedAtBefore(LocalDateTime expiry);
}
