package com.example.jobPortal.jobApp;

import com.example.jobPortal.jobApp.Job.Job;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Flow;

@SpringBootApplication
public class JobAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobAppApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner initialCreate(){
//		return (args)->{
//			Job j1 = new Job(1L, "SDE1", "sftwr engg", "100K", "200k", "Remote");
//		};
//	}

}
