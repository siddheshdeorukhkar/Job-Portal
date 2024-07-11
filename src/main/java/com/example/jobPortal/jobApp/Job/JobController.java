package com.example.jobPortal.jobApp.Job;

import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {
    private List<Job> jobs= new ArrayList<>();

    @GetMapping("/jobs")
    public List<Job>findAll(){
//        List <Job> j1= jobs;
        return jobs;
    }

    @PostMapping("/jobs")
    public String createJobs(@RequestBody Job job){
        jobs.add(job);
        return "Job added successfully!";

    }
}
