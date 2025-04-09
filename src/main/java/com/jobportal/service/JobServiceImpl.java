package com.jobportal.service;

import com.jobportal.dto.Job.CreateJobRequest;
import com.jobportal.dto.Job.JobResponseDTO;
import com.jobportal.entity.Job;
import com.jobportal.entity.User;
import com.jobportal.exceptions.JobPortalException;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void createJob(CreateJobRequest jobRequest, String employerEmail) throws JobPortalException {
        Job newJob = new Job();
        newJob.setTitle(jobRequest.getTitle());
        newJob.setDescription(jobRequest.getDescription());
        newJob.setLocation(jobRequest.getLocation());
        newJob.setCompanyName(jobRequest.getCompanyName());
        newJob.setPostedByEmail(employerEmail);
        newJob.setPostedAt(LocalDateTime.now());

        jobRepository.save(newJob);
    }

    @Override
    public List<JobResponseDTO> getAllJobs() throws JobPortalException {
        List<Job> jobs = jobRepository.findAll();

        return jobs.stream().map(job -> {
            User employer = userRepository.findByEmail(job.getPostedByEmail()).orElse(null);

            JobResponseDTO dto = new JobResponseDTO();
            BeanUtils.copyProperties(job, dto);

            if (employer != null) {
                dto.setEmployerName(employer.getName());
                dto.setEmployerRole(employer.getRole().toString());
            }

            return dto;
        }).toList();
    }

    @Override
    public JobResponseDTO getJobById(String id) throws JobPortalException {
        Job job = jobRepository.findById(id).orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND"));
        User user = userRepository.findByEmail(job.getPostedByEmail()).orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));

        JobResponseDTO dto = new JobResponseDTO();
        BeanUtils.copyProperties(job, dto);
        dto.setEmployerName(user.getName());
        dto.setEmployerRole(user.getRole().toString());
        return dto;
    }

    @Override
    public void updateJob(String jobId, CreateJobRequest request, String employerEmail) throws JobPortalException {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND"));

        //  check: Only employers can update job.
        if(!job.getPostedByEmail().equals(employerEmail)) throw new JobPortalException("UNAUTHORIZED");

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setLocation(request.getLocation());
        job.setCompanyName(request.getCompanyName());


        jobRepository.save(job);
    }

    @Override
    public void deleteJob(String jobId, String employerEmail) throws JobPortalException {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND"));

        //  check: Only employers can update job.
        if(!job.getPostedByEmail().equals(employerEmail)) throw new JobPortalException("UNAUTHORIZED");

        jobRepository.delete(job);
    }

}
