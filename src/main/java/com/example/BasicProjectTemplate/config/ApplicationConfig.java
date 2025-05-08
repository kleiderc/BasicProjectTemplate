/**
 * 
 */
package com.example.BasicProjectTemplate.config;

import javax.sql.DataSource;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Spring application context configuration.
 */
@Configuration
@EnableScheduling  // Should be used with @Configuration annotation
public class ApplicationConfig {

	@Bean
	public JobRepository jobRepository(DataSource dataSource, PlatformTransactionManager transactionManager) throws Exception {
	    JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
	    factory.setDataSource(dataSource);
	    factory.setTransactionManager(transactionManager);
	    factory.setTablePrefix("basicprojecttemplate.BATCH_"); // prefix with schema
	    factory.afterPropertiesSet();
	    return factory.getObject();
	}
}
