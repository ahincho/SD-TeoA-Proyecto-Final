package com.unsa.bank.application.ports;

import com.unsa.bank.domain.entities.Account;
import java.util.List;

public interface AccountService {

    List<Account> getAll();
    Account getAccountById(Long id);
    Account saveAccount(Account account);
    Account updateAccount(Long id, Account account);
    Account deleteAccount(Long id);
    Account addBalance(Long id, Double balance);
    Account decreaseBalance(Long id, Double balance);

}
