package com.example.BasicProjectTemplate.factory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.transaction.PlatformTransactionManager;

public class BatchFactory {

	// Generic Job method (not a @Bean)
	public static <T> Job buildGenericJob(String jobName, JobRepository jobRepository, Step step) {

		return new JobBuilder(jobName, jobRepository).start(step).build();
	}

	// Generic step method (not a @Bean)
	public static <T, O> Step buildGenericStep(String stepName, JobRepository jobRepository,
			PlatformTransactionManager transactionManager, ItemReader<T> reader, ItemProcessor<T, O> processor,
			ItemWriter<O> writer, int chunkSize) {

		return new StepBuilder(stepName, jobRepository).<T, O>chunk(chunkSize, transactionManager).reader(reader)
				.processor(processor).writer(writer).build();
	}
}
