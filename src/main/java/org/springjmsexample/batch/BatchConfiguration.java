package org.springjmsexample.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springjmsexample.batch.entity.mapper.PersonMapper;
import org.springjmsexample.batch.processor.PersonProcessor;
import org.springjmsexample.batch.writer.PersonWriter;
import org.springjmsexample.model.Person;

@EnableBatchProcessing
@Configuration
public class BatchConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;
	
  
	
	@Bean
	public JdbcCursorItemReader<Person> reader() {
		
	    JdbcCursorItemReader<Person> reader=new JdbcCursorItemReader<>();
		reader.setDataSource(dataSource);
		reader.setSql("select * from person ");
		reader.setRowMapper(new PersonMapper());
       return reader;
	}
	
	@Bean
	public PersonProcessor processor() {
		return new PersonProcessor();
	}
	
	@Bean
	public PersonWriter writer() {
		return new PersonWriter();
		
	}
	
	@Bean
	public Job cachingCustomerJob() {
		return jobBuilderFactory.get("cachingCustomerJob")
				.incrementer(new RunIdIncrementer())
				.flow(step1())
				.end()
				.build();
	}
	@Bean
	 public Step step1() {
	  return stepBuilderFactory.get("step1").<Person, Person> chunk(10)
	    .reader(reader())
	    .processor(processor())
	    .writer(writer())
	    .allowStartIfComplete(true)
	    .build();
	 }
}
