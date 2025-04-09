package com.jobportal.service;

import com.jobportal.dto.Job.CreateJobRequest;
import com.jobportal.dto.Job.JobResponseDTO;
import com.jobportal.exceptions.JobPortalException;

import java.util.List;

public interface JobService {
    void createJob(CreateJobRequest jobRequest, String employerEmail) throws JobPortalException;

    List<JobResponseDTO> getAllJobs() throws JobPortalException;

    JobResponseDTO getJobById(String id) throws JobPortalException;

    void updateJob(String jobId, CreateJobRequest request, String employerEmail)  throws JobPortalException;

    void deleteJob(String jobId,String employerEmail)  throws JobPortalException;

}
