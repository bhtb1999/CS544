package app;

import domain.School;
import domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import repositories.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain")
public class Application implements CommandLineRunner {

    @Autowired
    BookRepository bookrepository;

    @Autowired
    PublisherRepository publisherrepository;

    @Autowired
    DepartmentRepository departmentrepository;

    @Autowired
    EmployeeRepository employeerepository;

    @Autowired
    FlightRepository flightrepository;

    @Autowired
    PassengerRepository passengerrepository;

    @Autowired
    SchoolRepository schoolrepository;

    @Autowired
    StudentRepository studentrepository;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // public void displayBooks(List<Book> books) {
    // System.out.println("----------"+books);
    // for (Book book : books) {
    // System.out.println("--------------------------------------");
    // System.out.println("Book title: "+book.getTitle());
    // System.out.println("Book ISBN: "+book.getISBN());
    // System.out.println("Book author: "+book.getAuthor());
    // System.out.println("Book price: "+book.getPrice());
    // }
    // }

    @Override
    public void run(String... args) throws Exception {
//        Department department = new Department("IT");
//        departmentrepository.save(department); // Save department first
//
//        Employee emp1 = new Employee(1, "No1", department);
//        Employee emp2 = new Employee(2, "No2", department);
//        Employee emp3 = new Employee(3, "No3", department);
//
//        List<Employee> emps = new ArrayList<>();
//        emps.add(emp1);
//        emps.add(emp2);
//        emps.add(emp3);
//
//        employeerepository.save(emp1);
//        employeerepository.save(emp2);
//        employeerepository.save(emp3);
//
//        department.setEmps(emps);
//        departmentrepository.save(department); // Optionally update department with employees
//
//        for(Department dep: departmentrepository.findAll()){
//            System.out.println(dep.getEmps());
//        }
//
//
//        Publisher publisher = new Publisher("Thai");
//        publisherrepository.save(publisher);
//        Publisher publisher1 = new Publisher("Thailand");
//        publisherrepository.save(publisher1);
//
//        Book book = new Book("1st book of Thai", "1", "Thai");
//        book.setPublisher(publisher1);
//        bookrepository.save(book);
//        Book book1 = new Book("Book of unknown", "2", "");
//        bookrepository.save(book1);
//
//        Flight flight = new Flight(1,"Iowa","Vietnam",new Date());
//        flightrepository.save(flight);
//        Flight flight1 = new Flight(2,"Vietnam","Iowa",new Date());
//        flightrepository.save(flight1);
//        Flight flight2 = new Flight(3,"Chicago","Vietnam",new Date());
//        flightrepository.save(flight2);
//
//        List<Flight> flights = new ArrayList<>(List.of(flight,flight1,flight2));
//
//        Passenger p1 = new Passenger("Thai", flights);
//        passengerrepository.save(p1);

        Student student = new Student("John", "Doe");
        Student student1 = new Student("Jane", "Doe");
        Student student2 = new Student("Jack", "Doe");

        studentrepository.saveAll(List.of(student, student1, student2));
        Map<Integer, Student> studentMap = new HashMap<>();

        for(Student stu : studentrepository.findAll()) {
            studentMap.put(stu.getStudentId(),stu);
            System.out.println(stu);
        }

        School school = new School("MIU", studentMap);
        schoolrepository.save(school);

        System.out.println("Hihi:::"+schoolrepository.findAll().get(0).getStudentList());

    }
}
