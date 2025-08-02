package jms;

import jakarta.jms.ConnectionFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import java.util.Optional;


@SpringBootApplication
@EnableJms
public class SpringJmsPersonSenderApplication implements CommandLineRunner {
	@Autowired
	JmsTemplate jmsTemplate;


	public static void main(String[] args)  {
		ConfigurableApplicationContext context = SpringApplication.run(SpringJmsPersonSenderApplication.class, args);
		context.close();
	}

	@Override
	public void run(String... args) throws Exception {
		Person person = new Person("Frank", "Brown");
		//convert person to JSON string
		ObjectMapper objectMapper = new ObjectMapper();
		String personAsString = objectMapper.writeValueAsString(person);

		System.out.println("Sending a JMS message:" + personAsString);
		jmsTemplate.convertAndSend("testQueue",personAsString);

//		Command command1 = new Command("+", 9);
//		Command command2 = new Command("-", 5);
//		ObjectMapper objectMapper1 = new ObjectMapper();
//		String commandAsString1 = objectMapper1.writeValueAsString(command1);
//		String commandAsString2 = objectMapper1.writeValueAsString(command2);
//		System.out.println("Sending a JMS command:" + commandAsString1);
//		System.out.println("Sending a JMS command:" + commandAsString2);
//		jmsTemplate.convertAndSend("testTopic", commandAsString1);
//		jmsTemplate.convertAndSend("testTopic", commandAsString2);


	}

}
