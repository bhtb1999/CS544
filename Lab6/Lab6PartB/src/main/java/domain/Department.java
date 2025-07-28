package domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;


@Entity
public class Department {
    @Id
    @GeneratedValue
    private long id;
    private String name;


    public Department() {

    }

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                ", name='" + name + '\'' +
                '}';
    }
}
