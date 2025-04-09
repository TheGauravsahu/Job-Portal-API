package com.jobportal.controller;

import com.jobportal.dto.Job.CreateJobRequest;
import com.jobportal.dto.Job.JobResponseDTO;
import com.jobportal.dto.ResponseDTO;
import com.jobportal.exceptions.JobPortalException;
import com.jobportal.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/jobs")
@Validated
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping
    public ResponseEntity<ResponseDTO> createJob(@RequestBody @Valid CreateJobRequest jobRequest, Authentication authentication) throws JobPortalException {
        String email = authentication.getName();
        jobService.createJob(jobRequest,email);
        return ResponseEntity.ok(new ResponseDTO("Job created successfully."));
    }

    @GetMapping
    public ResponseEntity<List<JobResponseDTO>> listJobs () throws JobPortalException {
        List<JobResponseDTO> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponseDTO> getJobById(@PathVariable String id) throws JobPortalException{
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateJOb(@PathVariable String id, Authentication authentication, @RequestBody @Valid CreateJobRequest jobRequest) throws JobPortalException{
        String email = authentication.getName();
        jobService.updateJob(id,jobRequest,email);
        return ResponseEntity.ok(new ResponseDTO("Job updated successfully."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteJob(@PathVariable String id, Authentication authentication) throws JobPortalException{
        String email = authentication.getName();
        jobService.deleteJob(id,email);
        return ResponseEntity.ok(new ResponseDTO("Job deleted successfully."));
    }

}
