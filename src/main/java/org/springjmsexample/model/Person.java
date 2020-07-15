package org.springjmsexample.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	public int id;
	public String first_name;
	public String last_name;
	public String email;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date joined_date;
	
	
	
	public Person() {
		
	}
	public Person(int id, String first_name, String last_name, String email, Date joined_date) {
		
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.joined_date = joined_date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getJoined_date() {
		return joined_date;
	}
	public void setJoined_date(Date joined_date) {
		this.joined_date = joined_date;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
				+ ", joined_date=" + joined_date + "]";
	}
	

	
	

}
