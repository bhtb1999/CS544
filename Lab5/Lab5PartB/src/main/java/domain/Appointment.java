package domain;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

@Entity
public class Appointment {
	@Id
	@GeneratedValue
	private int id;
	private String appdate;
	private Payment payment;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "patient")
	private Patient patient;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "doctor")
	private Doctor doctor;

	public Appointment() {
	}

	public Appointment(String appdate, Patient patient, Payment payment,
			Doctor doctor) {
		this.appdate = appdate;
		this.patient = patient;
		this.payment = payment;
		this.doctor = doctor;
	}


	public String getAppdate() {
		return appdate;
	}

	public void setAppdate(String appdate) {
		this.appdate = appdate;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

}
