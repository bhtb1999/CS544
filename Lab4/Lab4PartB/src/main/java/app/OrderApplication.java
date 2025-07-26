package app;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.*;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain")
public class OrderApplication implements CommandLineRunner {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AddressRepository addressRepository;

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Product product = new Product(1, "Hibernate 3", "Good book on Hibernate", 35.5);
//        productRepository.save(product);
//		product.setName("Hibernate 3");
//		product.setDescription("Good book on Hibernate");
//		product.setPrice(35.50);
        OrderLine ol1 = new OrderLine(2, product);
//        orderLineRepository.save(ol1);

//		Product product2 = new Product();
        Product product2 = new Product(2, "Spring Boot", "Good book on Spring", 12.96);
//        productRepository.save(product2);
        //		product2.setName("The best of Queen");
//		product2.setDescription("Album from 1995");
//		product2.setPrice(12.98);
        OrderLine ol2 = new OrderLine(4, product2);
//        orderLineRepository.save(ol2);
        Order o1 = new Order("234743", "12/10/06", "open");
//        orderRepository.save(o1);
        o1.addOrderLine(ol1);
        o1.addOrderLine(ol2);
        Address address = new Address("Mainstreet 1", "New york", "43221");
//        addressRepository.save(address);
        Customer c1 = new Customer("Frank", "Brown", address);
//        customerRepository.save(c1);
        c1.addOrder(o1);
        o1.setCustomer(c1);

        orderRepository.save(o1);
        printOrder(o1);
    }

    public static void printOrder(Order order) {
        System.out.println("Order with orderNumber: " + order.getOrderNumber());
        System.out.println("Order date: " + order.getDate());
        System.out.println("Order status: " + order.getStatus());
        Customer cust = order.getCustomer();
        System.out.println("Customer: " + cust.getFirstname() + " " + cust.getLastname());
        for (OrderLine orderline : order.getOrderlines()) {
            System.out.println("Order line: quantity= " + orderline.getQuantity());
            Product product = orderline.getProduct();
            System.out.println("Product: " + product.getName() + " " + product.getDescription() + " " + product.getPrice());
        }

    }
}
