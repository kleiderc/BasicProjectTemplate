/**
 * 
 */
package com.example.BasicProjectTemplate.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 
 */
@Service
public class ServiceSchedule {

	private static final Logger log = LoggerFactory.getLogger(ServiceSchedule.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(cron = "* 0/30 * * * ?") // fixedRate = 5000
	@Async
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
	}
}
