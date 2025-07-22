package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private ILogger logger;
    private IEmailSender emailSender;

    @Autowired
    public ProductService(Logger logger) {
        this.logger = logger;
    }

    @Autowired
    public void setEmailSender(IEmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void addProduct(String name, String email) {
        logger.log("Creating product:" + name);
        emailSender.sendEmail(email, "Create " + name + " as a new product");

    }

    public void saveToDB() {
        System.out.println("Save to Database");
        logger.log("Product is saved in the DB");
    }

}
