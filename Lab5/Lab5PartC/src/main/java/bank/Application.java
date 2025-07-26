package bank;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import bank.domain.Account;
import bank.domain.AccountEntry;
import bank.domain.Customer;
import bank.dto.AccountDTO;
import bank.repositories.AccountRepository;
import bank.repositories.CustomerRepository;
import bank.service.AccountService;
import bank.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AccountService accountService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Customer c1 = new Customer("N1");
        Customer c2 = new Customer("N2");
        Customer c3 = new Customer("N3");
        Customer c4 = new Customer("N4");
        customerRepository.saveAll(List.of(c1, c2, c3, c4));


        Account a1 = new Account(1900, List.of(), c1);
        Account a2 = new Account(2900, List.of(), c2);
        Account a3 = new Account(3900, List.of(), c3);
        Account a4 = new Account(4900, List.of(), c4);
        accountRepository.saveAll(List.of(a1, a2, a3, a4));

        AccountEntry accountEntry1 = new AccountEntry(new Date(), 100, "h", a1.getAccountNumber(), a2.getAccountNumber());
        AccountEntry accountEntry2 = new AccountEntry(new Date(), 200, "Hello World", a2.getAccountNumber(), a3.getAccountNumber());
        a1.setEntryList(List.of(accountEntry1, accountEntry2));
        accountRepository.save(a1);
        AccountDTO accountDTO1 = new AccountDTO(1900, List.of(accountEntry1), c1);
        accountService.createAccount(accountDTO1);
//        accountService.deposit(accountDTO1, 100);




//		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//		IAccountService accountService = context.getBean("accountService", AccountService.class);
//
//		// create 2 accounts;
//		accountService.createAccount(1263862, "Frank Brown");
//		accountService.createAccount(4253892, "John Doe");
//		//use account 1;
//		accountService.deposit(1263862, 240);
//		accountService.deposit(1263862, 529);
//		accountService.withdrawEuros(1263862, 230);
//		//use account 2;
//		accountService.deposit(4253892, 12450);
//		accountService.depositEuros(4253892, 200);
//		accountService.transferFunds(4253892, 1263862, 100, "payment of invoice 10232");
//		// show balances
//
//		Collection<Account> accountlist = accountService.getAllAccounts();
//		Customer customer = null;
//		for (Account account : accountlist) {
//			customer = account.getCustomer();
//			System.out.println("Statement for Account: " + account.getAccountnumber());
//			System.out.println("Account Holder: " + customer.getName());
//			System.out.println("-Date-------------------------"
//							+ "-Description------------------"
//							+ "-Amount-------------");
//			for (AccountEntry entry : account.getEntryList()) {
//				System.out.printf("%30s%30s%20.2f\n", entry.getDate()
//						.toString(), entry.getDescription(), entry.getAmount());
//			}
//			System.out.println("----------------------------------------"
//					+ "----------------------------------------");
//			System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:",
//					account.getBalance());
//		}
    }

}


