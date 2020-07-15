package org.springjmsexample.publisher;

import java.util.List;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springjmsexample.batch.writer.PersonWriter;
import org.springjmsexample.listener.Consumer;
import org.springjmsexample.model.Person;


/**
 * This controller basically used for publishing message on Activemq
 * From there we have Listener that will trigger the batch job and fetch the data from db 
 * if data is not present already in the cache.
 * @author Kamil
 *
 */
@RestController
@RequestMapping("/message")
public class MessagePublisher {
	
	@Autowired private JmsTemplate jmsTemplate;
	
	@Autowired
	Queue queue;
	
	@Autowired
	Consumer consumer;
	
	@GetMapping
	@RequestMapping("/retrieve/{id}")
	@Cacheable("person")
	private Person getPersonWithId(@PathVariable final int id){
		System.out.println("Id is "+id);
		jmsTemplate.convertAndSend(queue,id);
      Person person= PersonWriter.getListOfPerson().stream()
    		  .filter(p->p.getId()==id)
    		  .findFirst()
    		  .orElseThrow(IllegalArgumentException::new);
		return person;
	}
 

}
