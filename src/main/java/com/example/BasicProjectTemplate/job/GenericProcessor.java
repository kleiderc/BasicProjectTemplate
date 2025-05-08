/**
 * 
 */
package com.example.BasicProjectTemplate.job;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import com.example.BasicProjectTemplate.model.UserCsvImport;

/**
 * @param <T>
 * 
 */
public class GenericProcessor<T> implements ItemProcessor<T, T> {
	@Override
	public T process(T item) {
		System.out.println(" **************************** GenericProcessor");
	
		return item;
	}
	
	/**
	 * To map a FieldSet into a UserCsvImport object, a FieldSetMapper that returns
	 * UserCsvImports needs to be defined.
	 * 
	 * @param <T>
	 */
	public static class CsvImportFieldSetMapper<T> implements FieldSetMapper<T> {

		public T mapFieldSet(FieldSet fieldSet) {

			UserCsvImport userCsvImport = new UserCsvImport();

			userCsvImport.setId(fieldSet.readInt(0));
			userCsvImport.setLastName(fieldSet.readString(1));
			userCsvImport.setFirstName(fieldSet.readString(2));
			userCsvImport.setPosition(fieldSet.readString(3));
			userCsvImport.setBirthYear(fieldSet.readInt(4));

			return (T) userCsvImport;
		}
	}
}
