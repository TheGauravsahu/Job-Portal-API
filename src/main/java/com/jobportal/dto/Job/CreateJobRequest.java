package com.jobportal.dto.Job;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class CreateJobRequest {
    @NotBlank(message = "Job title cannot be blank.")
    private String title;

    @NotBlank(message = "Job description cannot be blank.")
    private String description;

    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be a positive number")
    private Long salary;


    @NotNull(message = "Salary frequency is required.")
    private SalaryFrequency salaryFrequency;



    @NotEmpty(message = "Skills list cannot be empty.")
    private List<String> skills;

    @NotBlank(message = "Category cannot be blank.")
    private String category;

    // Company
    @NotBlank(message = "Company name cannot be blank.")
    private String companyName;

    @NotBlank(message = "Company logo URL cannot be blank.")
    private String companyLogo;

    @NotBlank(message = "Company location cannot be blank.")
    private String companyLocation;

    // Enums
    @NotNull(message = "Employment type is required.")
    private EmploymentType employmentType;

    @NotNull(message = "Workplace type is required.")
    private WorkPlaceType workplaceType;
}
