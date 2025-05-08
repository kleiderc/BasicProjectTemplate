/**
 * 
 */
package com.example.BasicProjectTemplate.job;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import com.example.BasicProjectTemplate.model.UserCsvImport;

/**
 * @param <T>
 * 
 */
public class GenericWriter<T, O> implements ItemWriter<T> {

	@Override
	public void write(Chunk<? extends T> chunk) throws Exception {
		System.out.println(" **************************** Chunk");
		chunk.forEach(System.out::println);
		 for (T item : chunk) {
			 if (item instanceof UserCsvImport user) {
	                System.out.printf("User ID: %d, Name: %s, Email: %s%n",
	                    user.getId(), user.getLastName(), user.getFirstName(), user.getPosition());
	            }
	        }
	}
}
