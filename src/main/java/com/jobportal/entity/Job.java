package com.jobportal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "jobs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    @Id
    private String id;

    private String title;
    private String description;
    private String location;
    private String companyName;
    private String postedByEmail;
    private LocalDateTime postedAt;
}
