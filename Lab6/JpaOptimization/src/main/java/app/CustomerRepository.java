package app;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Customer c set c.name = :name")
    public void bulkUpdateName(@Param("name") String name);

}




