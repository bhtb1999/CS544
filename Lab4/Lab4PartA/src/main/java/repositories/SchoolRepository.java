package repositories;

import domain.Publisher;
import domain.School;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SchoolRepository extends JpaRepository<School, Integer> {
}




