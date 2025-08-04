package jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CommandListener {
    private int total = 0;
    @JmsListener(destination = "testTopic")
    public void receiveCommand(final String commandAsString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Command command = objectMapper.readValue(commandAsString, Command.class);
            String operator = command.getOperator();
            int value = command.getValue();
            if(operator.equals("+")) {
                total += value;
            }
            else if(operator.equals("-")) {
                total -= value;
            }
            else if(operator.equals("*")) {
                total *= value;
            }
            System.out.println("JMS receiver received message:" + command.getOperator()+" "+command.getValue());
            System.out.println("Result: " + total);
        } catch (IOException e) {
            System.out.println("JMS receiver: Cannot convert : " + commandAsString+" to a Command object");
        }
     }
}

