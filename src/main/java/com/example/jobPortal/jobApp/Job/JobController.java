package com.example.jobPortal.jobApp.Job;

import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

//    private List<Job> jobs= new ArrayList<>();

//    @GetMapping("/jobs")
//    public List<Job>findAll(){
////        List <Job> j1= jobs;
////        return jobs;
//        List<Job> jobs= jobService.findAll();
//        return jobs;
//    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>>findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }

//    @GetMapping("/jobs/{id}")
//    public Job findJobById(@PathVariable Long id){
//        Job job= jobService.findJobById(id);
//        return job;
//    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> findJobById(@PathVariable Long id){
        Job job= jobService.findJobById(id);
        if(job != null){
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //ResponseEntity
    //HttpStatus

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
//        jobs.add(job);
        jobService.createJob(job);
//        return "Job added successfully!";
        return new ResponseEntity<>("Job added successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String>deleteJob(@PathVariable Long id){
        boolean deleted = jobService.deleteJobById(id);
        if(deleted){
            return new ResponseEntity<>("job deleted with id " + id, HttpStatus.OK);
        }
        return new ResponseEntity<>("No job found" ,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<String>updateJobById(@PathVariable Long id,
                                               @RequestBody Job updatedJob){
        boolean updated= jobService.updateJob(id, updatedJob);
        if(updated){
            return new ResponseEntity<>("job updated", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("job wit this id not found", HttpStatus.NOT_FOUND);
    }


}
