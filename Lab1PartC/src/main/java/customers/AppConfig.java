package customers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CustomerService customerService() {
        CustomerService customerService = new CustomerService();
        Logger logger = new Logger();
        customerService.setCustomerDAO(customerDAO(logger));
        customerService.setEmailSender(emailSender(logger));
        return customerService;
    }

    @Bean
    public ICustomerDAO customerDAO(Logger logger) {
        return new CustomerDAO(logger);
    }

    @Bean
    public IEmailSender emailSender(Logger logger) {
        return new EmailSender(logger);
    }

}
