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
        Product product = new CD(1, "Hibernate 3", "Good book on Hibernate", 35.5,"Bao");
        OrderLine ol1 = new OrderLine(2, product);

        Product product2 = new DVD(2, "Spring Boot", "Good book on Spring", 12.96,"Fiction");
        Product product3 = new DVD(3, "Javascript", "Good book on Javascript", 12.96,"Science");

        OrderLine ol2 = new OrderLine(4, product2);
        OrderLine ol3 = new OrderLine(100, product3);

        Order o1 = new Order("234743", "12/10/06", "open");
        o1.addOrderLine(ol1);
        o1.addOrderLine(ol2);
        o1.addOrderLine(ol3);

        Address address = new Address("Mainstreet 1", "New york", "43221");
        Customer c1 = new Customer("Frank", "Brown", address);
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
