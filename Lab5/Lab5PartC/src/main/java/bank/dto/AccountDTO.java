package bank.dto;

import bank.domain.AccountEntry;
import bank.domain.Customer;

import java.util.Collection;

public record AccountDTO(long accountNumber, Collection<AccountEntry> entryList, Customer customer) {
}
