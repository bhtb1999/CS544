package repositories;

import domain.School;
import domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, Integer> {
}




