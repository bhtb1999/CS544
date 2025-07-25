package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Employee {
    @Id
    private int employeeNumber;
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department dep;

    protected Employee() {
    }

    public Employee(int employeeNumber, String name, Department department) {
        super();
        this.employeeNumber = employeeNumber;
        this.name = name;
        this.dep = department;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return dep;
    }

    public void setDepartment(Department department) {
        this.dep = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeNumber=" + employeeNumber +
                ", name='" + name + '\'' +
                ", dep=" + dep +
                '}';
    }
}
