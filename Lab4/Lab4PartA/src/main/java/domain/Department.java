package domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Department {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @OneToMany(mappedBy = "dep", fetch = FetchType.EAGER)
    private List<Employee> emps = new ArrayList<Employee>();

    protected Department() {
    }

    public Department(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmps() {
        return emps;
    }

    public void setEmps(List<Employee> emps) {
        this.emps = emps;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
