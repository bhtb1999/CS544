package repositories;

import domain.Flight;
import domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FlightRepository extends JpaRepository<Flight, Integer> {
}




