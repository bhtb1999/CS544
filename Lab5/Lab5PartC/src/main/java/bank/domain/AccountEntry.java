package bank.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.stereotype.Service;

import java.util.Date;
@Entity
public class AccountEntry {
	@Id
	@GeneratedValue
	private int id;
	private Date date;
	private double amount;
	private String description;
	private long fromAccountNumber;
	private long fromPersonName;
	
	protected AccountEntry() {
	}

	public AccountEntry(Date date, double amount, String description, long fromAccountNumber, long fromPersonName) {
		super();
		this.date = date;
		this.amount = amount;
		this.description = description;
		this.fromAccountNumber = fromAccountNumber;
		this.fromPersonName = fromPersonName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getFromAccountNumber() {
		return fromAccountNumber;
	}

	public void setFromAccountNumber(long fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}

	public long getFromPersonName() {
		return fromPersonName;
	}

	public void setFromPersonName(long fromPersonName) {
		this.fromPersonName = fromPersonName;
	}

	@Override
	public String toString() {
		return "AccountEntry{" +
				"id=" + id +
				", date=" + date +
				", amount=" + amount +
				", description='" + description + '\'' +
				", fromAccountNumber=" + fromAccountNumber +
				", fromPersonName=" + fromPersonName +
				'}';
	}
}
