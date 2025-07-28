package domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;


@Entity
public class Grade {
    @Id
    @GeneratedValue
    private long id;
    private Integer grade;

    @ManyToOne
    private Course course;

    public Grade() {

    }

    public Grade(Integer grade) {
        this.grade = grade;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "grade=" + grade +
                ", course=" + course +
                '}';
    }
}
