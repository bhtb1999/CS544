package repositories;

import domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> , JpaSpecificationExecutor<Customer> {
//    List<Customer> findByAddressZip(String zip);
//    List<Customer> findByTheordersOrderlinesProductName(String name);

    //Method names
    List<Customer> findAllCustomersBy();
    List<Customer> findByAddressZip(String zipcode);
    List<Customer> findByTheordersOrderlinesProductName(String name);

    //Named Queries
    List<Customer> getAllCustomersFrom(String country);

    // JPQL queries with @Query
    @Query("SELECT c.firstname, c.lastname FROM Customer c WHERE c.address.city = :city")
    List<String> getFNameAndLNameFrom(String city);

    @Query("SELECT o.ordernr from Customer c JOIN c.theorders o WHERE c.address.city = :city")
    List<String> getOrderNumbersByCity(@Param("city") String city);



    @Query("select c from Customer c where c.address.country = :country")
    List<Customer> getAllCustomersFromCountry(@Param("country")String country);

    @Query("select c from Customer c where c.address.city = :city")
    List<Customer> getAllCustomersFromCity(@Param("city")String amsterdam);

}
