package bank.domain;

import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Entity
public class Account {
	@Id
	private long accountNumber;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<AccountEntry> entryList = new ArrayList<AccountEntry>();
	@OneToOne(fetch = FetchType.EAGER)
	private Customer customer;


	protected Account() {}

	public Account(long accountNumber, Collection<AccountEntry> entryList, Customer customer) {
		this.accountNumber = accountNumber;
		this.entryList = entryList;
		this.customer = customer;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setEntryList(Collection<AccountEntry> entryList) {
		this.entryList = entryList;
	}

	public long getAccountnumber() {
		return accountNumber;
	}
	public void setAccountnumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getBalance() {
		double balance=0;
		for (AccountEntry entry : entryList) {
			balance+=entry.getAmount();
		}
		return balance;
	}
//	public void deposit(double amount){
//		AccountEntry entry = new AccountEntry(new Date(), amount, "deposit", "", "");
//		entryList.add(entry);
//	}
//	public void withdraw(double amount){
//		AccountEntry entry = new AccountEntry(new Date(), -amount, "withdraw", "", "");
//		entryList.add(entry);
//	}

	public void addEntry(AccountEntry entry){
		entryList.add(entry);
	}

//	public void transferFunds(Account toAccount, double amount, String description){
//		AccountEntry fromEntry = new AccountEntry(new Date(), -amount, description, ""+toAccount.getAccountnumber(), toAccount.getCustomer().getName());
//		AccountEntry toEntry = new AccountEntry(new Date(), amount, description, ""+toAccount.getAccountnumber(), toAccount.getCustomer().getName());
//		entryList.add(fromEntry);
//		toAccount.addEntry(toEntry);
//	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Collection<AccountEntry> getEntryList() {
		return entryList;
	}

	@Override
	public String toString() {
		return "Account{" +
				"accountNumber=" + accountNumber +
				", entryList=" + entryList +
				", customer=" + customer +
				'}';
	}
}
