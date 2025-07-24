package products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private ProductDAO productDao;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		productDao.clearDB();
		Supplier sp1 = new Supplier("First", "0918076668");
		Supplier sp2 = new Supplier("Second", "0918076668");
		Product product1 = new Product(101,"John doe", 132123);
		product1.setSupplier(sp1);
		productDao.save(product1);
		Product product2 = new Product(66,"James Johnson", 30000);
		product2.setSupplier(sp2);
		productDao.save(product2);
		System.out.println("Find product by product product number: " + productDao.findByProductNumber(101));
		System.out.println("Find product by product product number: " + productDao.findByProductNumber(66));
		System.out.println("Find product by product name: " + productDao.findByProductName("John doe"));
		if(productDao.removeProduct(66) == 1) {
			System.out.println("Deleted product with id 66");
		} else {
			System.out.println("Failed to delete product with id 66");
		}
		System.out.println("-----------All customers ----------------");
		System.out.println(productDao.getAllProducts());
	}
}
