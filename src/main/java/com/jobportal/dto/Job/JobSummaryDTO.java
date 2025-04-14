package com.jobportal.dto.Job;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class JobSummaryDTO {
    private String id;
    private String title;
    private Long salary;
    private SalaryFrequency salaryFrequency;
    private List<String> skills;

    // company
    private String companyName;
    private String companyLogo;
    private String companyLocation;

    private EmploymentType employmentType;
    private WorkPlaceType workplaceType;

    private LocalDateTime postedAt;
}
