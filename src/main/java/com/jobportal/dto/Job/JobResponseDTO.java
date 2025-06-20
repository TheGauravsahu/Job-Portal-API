package com.jobportal.dto.Job;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class JobResponseDTO {
    private String id;

    private String title;
    private String description;
    private Long salary;
    private SalaryFrequency salaryFrequency;
    private List<String> skills;
    private String category;

    // company
    private String companyName;
    private String companyLogo;
    private String companyLocation;

    // employer
    private String postedByEmail;
    private String employerName;

    // others
    private EmploymentType employmentType;
    private WorkPlaceType workplaceType;

    private LocalDateTime postedAt;

}
