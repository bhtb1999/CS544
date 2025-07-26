package bank.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import bank.adapter.AccountAdapter;
import bank.dao.AccountDAO;
import bank.dao.IAccountDAO;
import bank.domain.Account;
import bank.domain.AccountEntry;
import bank.domain.Customer;
import bank.dto.AccountDTO;
import bank.jms.IJMSSender;
import bank.jms.JMSSender;
import bank.logging.ILogger;
import bank.logging.Logger;
import bank.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountService{
    @Autowired
    private IAccountDAO accountDAO;
    @Autowired
    private ICurrencyConverter currencyConverter;
    @Autowired
    private IJMSSender jmsSender;
    @Autowired
    private ILogger logger;
    @Autowired
    AccountRepository accountRepository;

//    public AccountService(AccountDAO accountDAO, CurrencyConverter currencyConverter, JMSSender jmsSender, Logger logger) {
//        this.accountDAO = accountDAO;
//        this.currencyConverter = currencyConverter;
//        this.jmsSender = jmsSender;
//        this.logger = logger;
//    }

    public Account createAccount(AccountDTO accountDTO) {
        Account account = AccountAdapter.getAccountFromAccountDTO(accountDTO);
        accountRepository.save(account);
        return account;
    }


//    public void deposit(AccountDTO accountDTO, double amount) {
//        Account account = AccountAdapter.getAccountFromAccountDTO(accountDTO);
//        AccountEntry entry = new AccountEntry(new Date(), amount,"deposit",account.getAccountNumber(),account.getAccountNumber());
//        System.out.println(account);
//        account.getEntryList().add(entry);
////        account.addEntry(entry);
//        account.setEntryList(account.getEntryList());
//    }



//    public Account createAccount(long accountNumber, String customerName) {
//        Account account = new Account(accountNumber);
//        Customer customer = new Customer(customerName);
//        account.setCustomer(customer);
//        accountDAO.saveAccount(account);
//        logger.log("createAccount with parameters accountNumber= " + accountNumber + " , customerName= " + customerName);
//        return account;
//    }
//
//    public void deposit(long accountNumber, double amount) {
//        Account account = accountDAO.loadAccount(accountNumber);
//        account.deposit(amount);
//        accountDAO.updateAccount(account);
//        logger.log("deposit with parameters accountNumber= " + accountNumber + " , amount= " + amount);
//        if (amount > 10000) {
//            jmsSender.sendJMSMessage("Deposit of $ " + amount + " to account with accountNumber= " + accountNumber);
//        }
//    }

//    public Account getAccount(long accountNumber) {
//        Account account = accountDAO.loadAccount(accountNumber);
//        return account;
//    }
//
//    public Collection<Account> getAllAccounts() {
//        return accountDAO.getAccounts();
//    }

//    public void withdraw(long accountNumber, double amount) {
//        Account account = accountDAO.loadAccount(accountNumber);
//        account.withdraw(amount);
//        accountDAO.updateAccount(account);
//        logger.log("withdraw with parameters accountNumber= " + accountNumber + " , amount= " + amount);
//    }

//    public void depositEuros(long accountNumber, double amount) {
//        Account account = accountDAO.loadAccount(accountNumber);
//        double amountDollars = currencyConverter.euroToDollars(amount);
//        account.deposit(amountDollars);
//        accountDAO.updateAccount(account);
//        logger.log("depositEuros with parameters accountNumber= " + accountNumber + " , amount= " + amount);
//        if (amountDollars > 10000) {
//            jmsSender.sendJMSMessage("Deposit of $ " + amount + " to account with accountNumber= " + accountNumber);
//        }
//    }

//    public void withdrawEuros(long accountNumber, double amount) {
//        Account account = accountDAO.loadAccount(accountNumber);
//        double amountDollars = currencyConverter.euroToDollars(amount);
//        account.withdraw(amountDollars);
//        accountDAO.updateAccount(account);
//        logger.log("withdrawEuros with parameters accountNumber= " + accountNumber + " , amount= " + amount);
//    }
//
//    public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
//        Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
//        Account toAccount = accountDAO.loadAccount(toAccountNumber);
//        fromAccount.transferFunds(toAccount, amount, description);
//        accountDAO.updateAccount(fromAccount);
//        accountDAO.updateAccount(toAccount);
//        logger.log("transferFunds with parameters fromAccountNumber= " + fromAccountNumber + " , toAccountNumber= " + toAccountNumber + " , amount= " + amount + " , description= " + description);
//        if (amount > 10000) {
//            jmsSender.sendJMSMessage("TransferFunds of $ " + amount + " from account with accountNumber= " + fromAccount + " to account with accountNumber= " + toAccount);
//        }
//    }
}
