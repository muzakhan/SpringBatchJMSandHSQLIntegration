package org.springjmsexample.listener;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springjmsexample.batch.writer.PersonWriter;
import org.springjmsexample.model.Person;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Configuration
public class Consumer {

  
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	@JmsListener(destination = "standalone.queue", containerFactory = "jsaFactory")
	public void consume(Message message) throws JMSException, JsonMappingException, JsonProcessingException {

		if (message instanceof TextMessage) {
			String jsonMessage = ((TextMessage) message).getText();
			ObjectMapper mapper = new ObjectMapper();
			int id = mapper.readValue(jsonMessage, Integer.class);
			triggerBatch(id);
			//System.out.println(listOfPerson);
			//Person person=listOfPerson.stream().filter(p->p.getId()==1).findFirst().orElseThrow(IllegalStateException::new);;
		  
		}

		else {

			throw new IllegalStateException();
		}

	}

	private void triggerBatch(int id) {
	  System.out.println("Triggering Batch job :"+id);

		try {
			jobLauncher.run(job, new JobParameters());
		} catch (JobExecutionAlreadyRunningException e) {

			e.printStackTrace();
		} catch (JobRestartException e) {

			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {

			e.printStackTrace();
		} catch (JobParametersInvalidException e) {

			e.printStackTrace();
		}
		

	}

}
