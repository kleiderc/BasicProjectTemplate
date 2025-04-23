/**
 * 
 */
package com.example.BasicProjectTemplate.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.BasicProjectTemplate.controller.CsvMappingUtil;

/**
 * 
 */
@Service
public class FileServiceSchedule {

	private static final Logger log = LoggerFactory.getLogger(FileServiceSchedule.class);

	@Scheduled(cron = "*/30 * * * * *") // fixedRate = 5000
	@Async
	public void reportFileServiceSchedule() {
		
		log.info("Scheduled task reportFileServiceSchedule started.");
		
		try {
			CsvMappingUtil.getUserCsvImportFromExcelFile("src/main/resources/userExcel.csv");
		} catch (Exception exception) {
			log.info("Error mapping {}.", exception);
		}
		
		log.info("Scheduled task reportFileServiceSchedule finished.");
	}
}
