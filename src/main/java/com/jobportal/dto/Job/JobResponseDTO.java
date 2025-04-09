package com.jobportal.dto.Job;

import lombok.Data;

@Data
public class JobResponseDTO {
    private String id;
    private String title;
    private String description;
    private String location;
    private String companyName;
    private String postedByEmail;
    private String employerName;
    private String employerRole;
}
