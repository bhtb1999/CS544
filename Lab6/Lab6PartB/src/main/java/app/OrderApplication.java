package app;

import domain.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.*;

import java.util.Iterator;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain")
public class OrderApplication implements CommandLineRunner {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Course course1 = new Course("Course 1");
        Course course2 = new Course("Course 2");
        Course course3 = new Course("Course 3");

        courseRepository.saveAll(List.of(course1, course2, course3));


        Grade grade1 = new Grade(5);
        Grade grade2 = new Grade(6);
        Grade grade3 = new Grade(7);
        Grade grade4 = new Grade(8);
        Grade grade5 = new Grade(9);

        grade1.setCourse(course1);
        grade2.setCourse(course2);
        grade3.setCourse(course3);
        grade4.setCourse(course3);
        grade5.setCourse(course3);


        Department department = new Department("Department 1");
        Department department2 = new Department("Department 2");

        Student student1 = new Student(1L, "Student 1");
        Student student2 = new Student(2L, "Student 2");

        student1.setDepartment(department);
        student1.setGrades(List.of(grade1, grade2, grade5));

        student2.setDepartment(department2);
        student2.setGrades(List.of(grade3, grade4));

        studentRepository.save(student1);
        studentRepository.save(student2);

        System.out.println("------All -------------------");
        List<Student> students = studentRepository.findByDepartment(department2);
        students.stream().forEach(System.out::println);

        System.out.println("------All -------------------");
        List<Student> students1 = studentRepository.getByDepartment(department2);
        students1.stream().forEach(System.out::println);

        System.out.println("------All -------------------");
        List<Student> studentsByCourseAndName = studentRepository.findStudentByGradesCourseAndName(course1, "Student 1");
        studentsByCourseAndName.stream().forEach(System.out::println);

        System.out.println("------All -------------------");
        List<Student> studentsByCourseAndName1 = studentRepository.getStudentByGradesCourseAndName(course1, "Student 1");
        studentsByCourseAndName1.stream().forEach(System.out::println);



    }

}

