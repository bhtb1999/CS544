package client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

@Component
public class BankGateway {
    RestTemplate restTemplate = new RestTemplate();
    private String serverUrl = "http://localhost:8080/";

    public AccountDTO createAccount(long accountNumber, String customerName) {
        restTemplate.postForLocation(serverUrl+"/createAccount?accountNumber={accountNumber}&customerName={customerName}",AccountDTO.class,accountNumber, customerName);
        return getAccount(accountNumber);
    }


    public void deposit(long accountNumber, double amount) {
        AccountCommand accountCommand = new AccountCommand(accountNumber, amount, "deposit");
        restTemplate.postForLocation(serverUrl+"/accounts",accountCommand);
    }

    public AccountDTO getAccount(long accountNumber) {
        AccountDTO accountDTO= restTemplate.getForObject(serverUrl+"accounts/{accountNumber}", AccountDTO.class, accountNumber);
        return accountDTO;
    }

    public Collection<AccountDTO> getAllAccounts() {
        Accounts accounts= restTemplate.getForObject(serverUrl+"accounts", Accounts.class);
        return accounts.getAccountList();
    }


    public void withdraw(long accountNumber, double amount) {
        AccountCommand accountCommand = new AccountCommand(accountNumber, amount, "withdraw");
        restTemplate.postForLocation(serverUrl+"/accounts",accountCommand);
    }


    public void depositEuros(long accountNumber, double amount) {
        AccountCommand accountCommand = new AccountCommand(accountNumber, amount, "depositEuros");
        restTemplate.postForLocation(serverUrl+"/accounts",accountCommand);
    }

    public void withdrawEuros(long accountNumber, double amount) {
        AccountCommand accountCommand = new AccountCommand(accountNumber, amount, "withdrawEuros");
        restTemplate.postForLocation(serverUrl+"/accounts",accountCommand);
    }

    public void transferFunds(long fromAccountNumber, long toAccountNumber,
                              double amount, String description) {
        AccountCommand accountCommand = new AccountCommand(fromAccountNumber, amount, "transferFunds",toAccountNumber,description);
        restTemplate.postForLocation(serverUrl+"/accounts",accountCommand);
    }
}
