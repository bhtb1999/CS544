package domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;


@Entity
public class Student {
    @Id
    @GeneratedValue
    private long id;
    private Long studentNumber;
    private String name;


    @OneToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Collection<Grade> grades = new ArrayList<Grade>();

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Department department;

    public Student() {

    }

    public Student(Long studentNumber, String name) {
        this.studentNumber = studentNumber;
        this.name = name;

    }

    public Long getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Long studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Collection<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Collection<Grade> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentNumber=" + studentNumber +
                ", name='" + name + '\'' +
                ", grades=" + grades +
                ", department=" + department +
                '}';
    }
}
