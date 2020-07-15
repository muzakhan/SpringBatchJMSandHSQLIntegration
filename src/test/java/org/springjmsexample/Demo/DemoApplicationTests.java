package org.springjmsexample.Demo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springjmsexample.batch.writer.PersonWriter;
import org.springjmsexample.model.Person;
import org.springjmsexample.publisher.MessagePublisher;


@SpringBootTest(classes = MessagePublisher.class)
@ComponentScan(basePackages = { "org.springjmsexample"})
@AutoConfigureMockMvc
public class DemoApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
/*	@MockBean
	private PersonWriter personWriter;*/
	
	Person person1=new Person(1,"Muzammil","Khan","muzammil.alig090@gmail.com",new Date());
	
	
	
	List<Person> listOfPerson=Arrays.asList(person1);
	
	@Test
	public void getPersonWithId() throws Exception{
		Mockito.when(PersonWriter.getListOfPerson()).thenReturn(listOfPerson);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/message/retrieve/1").accept(
				MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		
		String expected = "{id:1,first_name:Muzammil,last_name:khan}";

	    JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
		
		
	}

   
}
