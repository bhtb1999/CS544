package bank.adapter;

import bank.domain.Account;
import bank.dto.AccountDTO;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapter {
    public static Account getAccountFromAccountDTO(AccountDTO accountDTO) {
        return new Account(accountDTO.accountNumber(), accountDTO.entryList(), accountDTO.customer());
    }

    public static AccountDTO getAccountDTOFromAccount(Account account) {
        return new AccountDTO(account.getAccountNumber(), account.getEntryList(), account.getCustomer());
    }

    public static List<AccountDTO> getAccountDTOsFromAccounts(List<Account> accounts) {
        List<AccountDTO> accountDTOList = new ArrayList<AccountDTO>();
        for (Account account : accounts) {
            accountDTOList.add(getAccountDTOFromAccount(account));
        }
        return accountDTOList;
    }

    public static List<Account> getAccountsFromAccountDTOs(List<AccountDTO> accountDTOs){
        List<Account> accounts = new ArrayList<>();
        for(AccountDTO accountDTO : accountDTOs){
            accounts.add(getAccountFromAccountDTO(accountDTO));
        }
        return accounts;
    }
}
