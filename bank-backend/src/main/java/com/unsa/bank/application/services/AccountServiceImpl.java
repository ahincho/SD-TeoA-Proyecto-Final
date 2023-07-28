package com.unsa.bank.application.services;

import com.unsa.bank.domain.entities.Account;
import com.unsa.bank.domain.repositories.AccountRepository;
import com.unsa.bank.application.ports.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Long id, Account account) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isEmpty()) {
            return null;
        }
        Account accountToUpdate = optionalAccount.get();
        accountToUpdate.setUser(account.getUser());
        accountToUpdate.setBalance(account.getBalance());
        return accountRepository.save(accountToUpdate);
    }

    @Override
    public Account deleteAccount(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isEmpty()) {
            return null;
        }
        Account accountToDelete = optionalAccount.get();
        accountRepository.delete(accountToDelete);
        return accountToDelete;
    }

    @Override
    public Account addBalance(Long id, Double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount to be added must be greater than 0.");
        }
        Account account = accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No se encontró la cuenta con ID: " + id));
        account.setBalance(account.getBalance() + amount);
        return account;
    }

    @Override
    public Account decreaseBalance(Long id, Double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount to be added must be greater than 0.");
        }
        Account account = accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No se encontró la cuenta con ID: " + id));
        if (amount > account.getBalance()) {
            throw new IllegalArgumentException("Insufficient balance to make the withdrawal");
        }
        account.setBalance(account.getBalance() - amount);
        return account;
    }

}
