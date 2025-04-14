package com.jobportal.entity;

import com.jobportal.dto.Job.EmploymentType;
import com.jobportal.dto.Job.SalaryFrequency;
import com.jobportal.dto.Job.WorkPlaceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "jobs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    @Id
    private String id;

    private String title;
    private String description;
    private Long salary;
    private SalaryFrequency salaryFrequency = SalaryFrequency.MONTHLY;
    private List<String> skills;
    private String category;

    // company
    private String companyName;
    private String companyLogo;
    private String companyLocation;

    // employer
    private String postedByEmail;

    // others
    private EmploymentType employmentType = EmploymentType.Internship;
    private WorkPlaceType workplaceType = WorkPlaceType.On_site;

    private LocalDateTime postedAt;


}
