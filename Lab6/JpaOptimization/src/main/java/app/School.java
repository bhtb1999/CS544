package app;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class School {
	@Id
	@GeneratedValue
	private long id;
	private String name;

	@OneToMany(cascade = CascadeType.ALL)
	List<Student> studentList = new ArrayList<Student>();

	public School() {
	}

	public School(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	@Override
	public String toString() {
		return "School{" +
				"name='" + name + '\'' +
//				", studentList=" + studentList +
				'}';
	}
}
