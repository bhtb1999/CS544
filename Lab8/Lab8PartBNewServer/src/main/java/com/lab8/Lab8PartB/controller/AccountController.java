package com.lab8.Lab8PartB.controller;


import com.lab8.Lab8PartB.domain.Account;
import com.lab8.Lab8PartB.domain.AccountEntry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {
    private Map<String, Account> accounts = new HashMap<String, Account>();

    public AccountController() {

    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        accounts.put(account.getAccountnumber(), account);
        return account;
    }

    @PostMapping("/withdraw/{accountNumber}")
    public ResponseEntity<?> withdrawal(@RequestBody AccountEntry entry, @PathVariable String accountNumber) {
        double amount = entry.getAmount();
        Account account = accounts.get(accountNumber);
        System.out.println(account);
        double balance = account.getBalance();
        if(amount > balance) {
            throw new RuntimeException("You do not have enough funds to withdraw " + amount);
        }

        AccountEntry newEntry = new AccountEntry(-amount, "withdraw", accountNumber, "");
        account.getEntryList().add(newEntry);
        return new ResponseEntity<>("Withdraw successful", HttpStatus.OK);
    }

    @PostMapping("/transfer/{from}/{to}")
    public ResponseEntity<?> transfer(@RequestBody AccountEntry entry, @PathVariable String from, @PathVariable String to) {
        double amount = entry.getAmount();
        Account fromAccount = accounts.get(from);
        Account toAccount = accounts.get(to);
        double balance = fromAccount.getBalance();
        if(amount > balance) {
            throw new RuntimeException("You do not have enough funds to transfer " + amount);
        }
        AccountEntry fromEntry = new AccountEntry(-amount, "withdraw", fromAccount.getAccountnumber(), "");
        AccountEntry toEntry = new AccountEntry(amount, "transfer", toAccount.getAccountnumber(), "");
        fromAccount.getEntryList().add(fromEntry);
        toAccount.getEntryList().add(toEntry);
        return new ResponseEntity<>("Transfer successful", HttpStatus.OK);

    }

    @PostMapping("/deposit/{accountNumber}")
    public ResponseEntity<?> deposit(@RequestBody AccountEntry entry, @PathVariable String accountNumber) {
        double amount = entry.getAmount();
        Account account = accounts.get(accountNumber);
        AccountEntry newEntry = new AccountEntry(amount, "deposit", accountNumber, "");
        account.getEntryList().add(newEntry);
        System.out.println(account);
        return new ResponseEntity<>("Deposit successful", HttpStatus.OK);
    }


}
