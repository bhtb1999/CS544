package repositories;

import domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> , JpaSpecificationExecutor<Order> {
    @Query("select o.ordernr from Order o where o.status = 'closed'")
    List<String> getOrderNumbersFromOrdersWithStatusClosed();

//    @Query("select o.ordernr from Order o join o.orderlines ol where TYPE(ol.product) = CD")
//    List<String> getOrderNumbersFromOrdersWhichContainU2CDs();

    // JPQL queries with @Query
    @Query("SELECT o.ordernr FROM Order o WHERE o.status = 'closed'")
    List<String> findOrderNumbersWithStatusClosed();


    @Query("select o.ordernr from Order o where o.customer.address.city = :city")
    List<String> getOrderNumbersFromOrdersFromCity(@Param("city")String city);
}
