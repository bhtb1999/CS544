package bank;

import java.util.Collection;

import bank.domain.Account;
import bank.service.AccountAdapter;
import bank.service.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import bank.service.AccountService;

@Component
public class BankStatementPrinter {

    @Autowired
    private AccountService accountService;

    @Scheduled(fixedRate = 10000) // Every 10 seconds (10000 milliseconds)
    public void printBankStatements() {
        System.out.println("\n========== BANK STATEMENT REPORT ==========");
        System.out.println("Timestamp: " + new java.util.Date());
        System.out.println("===========================================");

        Collection<AccountDTO> accounts = accountService.getAllAccounts();

        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            for (AccountDTO account : accounts) {
                printAccountDetails(AccountAdapter.getAccountFromAccountDTO(account));
            }
        }

        System.out.println("=========== END OF REPORT =================\n");
    }

    private void printAccountDetails(Account account) {
        System.out.println("Account Number: " + account.getAccountnumber());
        System.out.println("Customer: " + account.getCustomer());
        System.out.println("Balance: $" + account.getBalance());
        System.out.println("-------------------------------------------");
    }
}