package prompt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import prompt.domains.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
