package com.example.BasicProjectTemplate.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

import com.example.BasicProjectTemplate.dto.UserCsvImportForm;
import com.example.BasicProjectTemplate.model.UserCsvImport;

@Component
public class UserMapper implements GenericMapper<UserCsvImportForm, UserCsvImport> {
	@Override
	public UserCsvImport map(UserCsvImportForm dto) {
		UserCsvImport entity = new UserCsvImport();
		entity.setId(dto.getId());
		entity.setLastName(dto.getLastName());
		entity.setFirstName(dto.getFirstName());
		entity.setPosition(dto.getPosition());
		entity.setBirthYear(dto.getBirthYear());
		return entity;
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