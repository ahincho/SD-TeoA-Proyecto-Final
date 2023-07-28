package com.unsa.bank.infraestructure.controllers;

import com.unsa.bank.application.ports.AccountService;
import com.unsa.bank.domain.entities.Account;
import com.unsa.bank.infraestructure.adapters.JpaAccountAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController implements JpaAccountAdapter {

    @Autowired
    AccountService accountService;

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Account> list() {
        return accountService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account getById(@PathVariable("id") Long id) {
        return accountService.getAccountById(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account save(@RequestBody Account account) {
        return accountService.saveAccount(account);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Account update(@PathVariable("id") Long id, @RequestBody Account account) {
        return accountService.updateAccount(id, account);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account delete(@PathVariable("id") Long id) {
        return accountService.deleteAccount(id);
    }

    @Override
    @PostMapping("/{id}/deposit")
    @ResponseStatus(HttpStatus.OK)
    public Account deposit(Long id, Double amount) {
        return accountService.addBalance(id, amount);
    }

    @Override
    @PostMapping("/{id}/withdraw")
    @ResponseStatus(HttpStatus.OK)
    public Account withdraw(Long id, Double amount) {
        return accountService.decreaseBalance(id, amount);
    }

}
