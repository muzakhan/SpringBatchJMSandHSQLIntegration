package org.springjmsexample.batch.entity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springjmsexample.model.Person;

public class PersonMapper implements RowMapper<Person> {

	@Override
	public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
		Person person=new Person();
		person.setId(rs.getInt("id"));
		person.setEmail(rs.getString("email"));
		person.setFirst_name(rs.getString("first_name"));
		person.setJoined_date(rs.getDate("joined_date"));
		return person;
	}

}
