/**
 * 
 */
package com.example.BasicProjectTemplate.job;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * @param <T>
 * 
 */
public class GenericReader<T> {

	public static <T> FlatFileItemReader<T> createReader(String filePath, FieldSetMapper<T> fieldSetMapper) {

		FlatFileItemReader<T> flatFileItemReader = new FlatFileItemReader<>();

		final Resource resource = new FileSystemResource(filePath);
		// The resource from which to read.
		flatFileItemReader.setResource(resource);

		flatFileItemReader.setLinesToSkip(1);

		DefaultLineMapper<T> defaultLineMapper = new DefaultLineMapper<>();

		// DelimitedLineTokenizer defaults to comma as its delimiter
		defaultLineMapper.setLineTokenizer(new DelimitedLineTokenizer());

		// The FieldSetMapper interface defines a single method, mapFieldSet,
		// which takes a FieldSet object and maps its contents to an object.
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);

		// Converts a String to an Object representing the item.
		flatFileItemReader.setLineMapper(defaultLineMapper);
		System.out.println("****************** FlatFileItemReader");
		return flatFileItemReader;
	}

	// To use public T read() without creating a loop in Spring Batch, you must
	// manage the reader's lifecycle manually — but this should only be done for
	// test/debug purposes, not inside production code or batch steps.
}
