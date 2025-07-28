package app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School, Long> {

    @Query("SELECT DISTINCT s FROM School s JOIN FETCH s.studentList")
    public List<School> getSchoolWithStudents();

}




