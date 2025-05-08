package com.example.BasicProjectTemplate.controller;

import java.util.UUID;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
public class BatchController {

	private final JobLauncher jobLauncher;
	private final Job userJob;

	@Autowired
	public BatchController(JobLauncher jobLauncher, Job userJob) {
		this.jobLauncher = jobLauncher;
		this.userJob = userJob;
	} 

	@GetMapping("/run")
	public ResponseEntity<String> runJob() {
		try {
			JobParameters parameters = new JobParametersBuilder().addString("runId", UUID.randomUUID().toString()) // always unique.addString("startYear", Year.now().toString()) 
					.toJobParameters();
			
			jobLauncher.run(userJob, parameters);
			return ResponseEntity.ok("Job triggered successfully!");
		} catch (Exception exception) {
			System.out.println("exception"+ exception);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Job failed!");
		}
	}
}