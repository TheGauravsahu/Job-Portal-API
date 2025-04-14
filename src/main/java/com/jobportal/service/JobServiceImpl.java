package com.jobportal.service;

import com.jobportal.dto.Job.CreateJobRequest;
import com.jobportal.dto.Job.JobResponseDTO;
import com.jobportal.dto.Job.JobSummaryDTO;
import com.jobportal.dto.UserRoles;
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
    public void createJob(CreateJobRequest req, String employerEmail) throws Exception {
        User user = userRepository.findByEmail(employerEmail)
                .orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));

        // Check if the user role is Employer
        if (user == null || !user.getRole().equals(UserRoles.EMPLOYER)) {
            throw new Exception("Only employers can create jobs.");
        }

        Job newJob = new Job(
                null,
                req.getTitle(),
                req.getDescription(),
                req.getSalary(),
                req.getSalaryFrequency(),
                req.getSkills(),
                req.getCategory(),
                req.getCompanyName(),
                req.getCompanyLogo(),
                req.getCompanyLocation(),
                employerEmail,
                req.getEmploymentType(),
                req.getWorkplaceType(),
                LocalDateTime.now()
        );

        jobRepository.save(newJob);
    }

    @Override
    public List<JobSummaryDTO> getAllJobs() throws JobPortalException {
        List<Job> jobs = jobRepository.findAll();

        return jobs.stream().map(job -> {
            JobSummaryDTO dto = new JobSummaryDTO();
            BeanUtils.copyProperties(job, dto);
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
        return dto;
    }

    @Override
    public void updateJob(String jobId, CreateJobRequest req, String employerEmail) throws JobPortalException {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND"));

        //  check: Only employers can update job.
        if (!job.getPostedByEmail().equals(employerEmail)) throw new JobPortalException("UNAUTHORIZED");

        job.setTitle(req.getTitle());
        job.setDescription(req.getDescription());
        job.setSalary(req.getSalary());
        job.setSalaryFrequency(req.getSalaryFrequency());
        job.setSkills(req.getSkills());
        job.setCategory(req.getCategory());
        job.setCompanyName(req.getCompanyName());
        job.setCompanyLogo(req.getCompanyLogo());
        job.setCompanyLocation(req.getCompanyLocation());
        job.setEmploymentType(req.getEmploymentType());
        job.setWorkplaceType(req.getWorkplaceType());
        job.setPostedAt(LocalDateTime.now());

        jobRepository.save(job);
    }

    @Override
    public void deleteJob(String jobId, String employerEmail) throws JobPortalException {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND"));

        //  check: Only employers can update job.
        if (!job.getPostedByEmail().equals(employerEmail)) throw new JobPortalException("UNAUTHORIZED");

        jobRepository.delete(job);
    }

}
