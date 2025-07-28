package repositories;

import domain.Course;
import domain.Department;
import domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
