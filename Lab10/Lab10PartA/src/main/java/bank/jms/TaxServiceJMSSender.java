package bank.jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class TaxServiceJMSSender implements IJMSSender {

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendJMSMessage(String text) {
        System.out.println("TaxServiceJMSSender: sending JMS message =" + text);
        jmsTemplate.convertAndSend("testTopic", text);

    }

}
