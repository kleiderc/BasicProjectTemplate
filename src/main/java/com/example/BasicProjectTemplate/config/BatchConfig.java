package com.example.BasicProjectTemplate.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.BasicProjectTemplate.factory.BatchFactory;
import com.example.BasicProjectTemplate.job.GenericProcessor;
import com.example.BasicProjectTemplate.job.GenericReader;
import com.example.BasicProjectTemplate.job.GenericWriter;
import com.example.BasicProjectTemplate.mapper.UserMapper;

@Configuration
@EnableBatchProcessing
public class BatchConfig<T> {

	private final JobRepository jobRepository;
	private final PlatformTransactionManager transactionManager;

	public BatchConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		this.jobRepository = jobRepository;
		this.transactionManager = transactionManager;
	}

	@Bean
	public Job userJob() {
		return BatchFactory.buildGenericJob("userJob", jobRepository, userStep());
	}

	@Bean
	public Step userStep() {

		FlatFileItemReader<T> reader = GenericReader.createReader("src/main/resources/userExcel.csv",new UserMapper.CsvImportFieldSetMapper<T>());

		return BatchFactory.buildGenericStep("userStep", jobRepository, transactionManager, reader,
				new GenericProcessor<>(), new GenericWriter<>(), 2);
	}
}