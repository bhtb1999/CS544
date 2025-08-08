package prompt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import prompt.domains.Profit;

import java.util.Date;
import java.util.List;

public interface ProfitRepository extends JpaRepository<Profit, Long> {

}
