package com.example.jobPortal.jobApp.Job;

import java.util.List;

public interface JobService {
    List<Job>findAll();
    void createJob(Job job);

    Job findJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}
