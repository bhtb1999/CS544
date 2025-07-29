package domain;

import jakarta.persistence.*;


@Entity
public class Appointment {
	@Id
	@GeneratedValue
	private int id;
	private String appdate;
	
	@OneToOne(cascade = { CascadeType.PERSIST })
	@JoinColumn(name="patient")
	private Patient patient;
	
	@Embedded
	private Payment payment;
	
	@OneToOne(cascade = { CascadeType.PERSIST })
	@JoinColumn(name="doctor")
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

	public int getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(int id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Appointment{" +
				"id=" + id +
				", appdate='" + appdate + '\'' +
				", patient=" + patient +
				", payment=" + payment +
				", doctor=" + doctor +
				'}';
	}
}
