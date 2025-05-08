package com.example.BasicProjectTemplate.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.example.BasicProjectTemplate.model.UserCsvImport;
import com.example.BasicProjectTemplate.service.FileServiceSchedule;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CsvMappingUtil {
	
	private static final Logger log = LoggerFactory.getLogger(FileServiceSchedule.class);
	

	/**
	 * To map a FieldSet into a UserCsvImport object, a FieldSetMapper that returns
	 * UserCsvImports needs to be defined.
	 */
	public static class UserCsvImportFieldSetMapper implements FieldSetMapper<UserCsvImport> {

		public UserCsvImport mapFieldSet(FieldSet fieldSet) {

			UserCsvImport userCsvImport = new UserCsvImport();

			userCsvImport.setId(fieldSet.readInt(0));
			userCsvImport.setLastName(fieldSet.readString(1));
			userCsvImport.setFirstName(fieldSet.readString(2));
			userCsvImport.setPosition(fieldSet.readString(3));
			userCsvImport.setBirthYear(fieldSet.readInt(4));

			return userCsvImport;
		}
	}

	public List<UserCsvImport> getUserCsvImportFromExcelFile(String sourceFile) throws Exception {

		List<UserCsvImport> userCsvImports = new ArrayList<UserCsvImport>();

		FlatFileItemReader<UserCsvImport> flatFileItemReader = new FlatFileItemReader<>();
		
		final Resource resource = new FileSystemResource(sourceFile); 
		// The resource from which to read.
		flatFileItemReader.setResource(resource);
		
		flatFileItemReader.setLinesToSkip(1);
		
		DefaultLineMapper<UserCsvImport> defaultLineMapper = new DefaultLineMapper<>();

		// DelimitedLineTokenizer defaults to comma as its delimiter
		defaultLineMapper.setLineTokenizer(new DelimitedLineTokenizer());

		// The FieldSetMapper interface defines a single method, mapFieldSet,
		// which takes a FieldSet object and maps its contents to an object.
		defaultLineMapper.setFieldSetMapper(new UserCsvImportFieldSetMapper());
		
		
		// Converts a String to an Object representing the item.
		flatFileItemReader.setLineMapper(defaultLineMapper);
		
		flatFileItemReader.open(new ExecutionContext());
	
		try {
			UserCsvImport userCsvImport = flatFileItemReader.read();
			
			while (userCsvImport != null) {
				
				userCsvImports.add(userCsvImport);
				
				userCsvImport = flatFileItemReader.read();
			}
		} catch (Exception exception) {
			log.info("Error readinng CVS file.", exception);
			throw exception;
		}
		
		for (UserCsvImport userCsvImport : userCsvImports) {
			log.info("Scheduled task info:" + userCsvImport.toString());
		}
		
		return userCsvImports;

	}

}
