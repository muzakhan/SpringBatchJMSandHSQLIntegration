package org.springjmsexample.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springjmsexample.model.Person;

public class PersonProcessor implements ItemProcessor<Person, Person> {
	

	@Override
	public Person process(Person item) throws Exception {

		return item;
	}

}
