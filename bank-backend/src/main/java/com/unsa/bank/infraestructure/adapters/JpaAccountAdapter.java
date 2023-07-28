package com.unsa.bank.infraestructure.adapters;

import com.unsa.bank.domain.entities.Account;

import java.util.List;

public interface JpaAccountAdapter {

    List<Account> list();
    Account getById(Long id);
    Account save(Account user);
    Account update(Long id, Account user);
    Account delete(Long id);
    Account deposit(Long id, Double amount);
    Account withdraw(Long id, Double amount);

}
