package repositories;

import domain.Course;
import domain.Department;
import domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    public List<Student> findByDepartment(Department department);

    public List<Student> findStudentByGradesCourseAndName(Course course, String name);

    @Query("SELECT s FROM Student s WHERE s.department = :department")
    public List<Student> getByDepartment(@Param("department") Department department);

    @Query("SELECT s FROM Student s JOIN s.grades g WHERE g.course = :course AND s.name = :name")
    public List<Student> getStudentByGradesCourseAndName(Course course, String name);
}
