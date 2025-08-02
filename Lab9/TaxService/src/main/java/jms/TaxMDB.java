package jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TaxMDB {
    @JmsListener(destination = "testTopic")
    public void receive(final String message) {
        System.out.println(message);
     }
}

