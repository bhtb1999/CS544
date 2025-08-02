package client;

import org.springframework.web.bind.annotation.RequestParam;

public class AccountCommand {
    private long accountNumber;
    private double amount;
    private String operation;
    private long toAccountNumber;
    private String description;

    public AccountCommand(long accountNumber, double amount, String operation) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.operation = operation;
    }

    public AccountCommand(long accountNumber, double amount, String operation, long toAccountNumber, String description) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.operation = operation;
        this.toAccountNumber = toAccountNumber;
        this.description = description;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public String getOperation() {
        return operation;
    }

    public long getToAccountNumber() {
        return toAccountNumber;
    }

    public String getDescription() {
        return description;
    }
}
