package com.jobportal.dto.Job;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateJobRequest {
    @NotBlank(message = "Job title cannot be blank.")
    private String title;

    @NotBlank(message = "Job description cannot be blank.")
    private String description;

    @NotBlank(message = "Job location cannot be blank.")
    private String location;

    @NotBlank(message = "Company name cannot be blank.")
    private String companyName;
}
