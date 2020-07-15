package org.springjmsexample.batch.writer;

import java.util.LinkedList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springjmsexample.model.Person;

@Component
public class PersonWriter implements ItemWriter<Person> {

	private static List<Person> listOfPerson = new LinkedList<>();

	public static List<Person> getListOfPerson() {
		return listOfPerson;
	}

	@Override
	public void write(List<? extends Person> items) throws Exception {
		listOfPerson.addAll(items);

	}

}
