package com.unsa.bank.infraestructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.unsa.bank.infraestructure.adapters.JpaAccountAdapter;
import com.unsa.bank.application.ports.AccountService;
import com.unsa.bank.domain.dtos.AccountRequest;
import com.unsa.bank.domain.dtos.AccountResponse;
import com.unsa.bank.domain.dtos.MovementRequest;
import com.unsa.bank.domain.dtos.MovementResponse;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController implements JpaAccountAdapter {

    @Autowired
    AccountService accountService;

    @Override
    @GetMapping
    public ResponseEntity<List<AccountResponse>> list() {
        List<AccountResponse> accounts = accountService.getAll();
        return accounts == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(accounts);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getById(@PathVariable("id") Long id) {
        AccountResponse account = accountService.getAccountById(id);
        return account == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(account);
    }

    @Override
    @PostMapping
    public ResponseEntity<AccountResponse> create(@RequestBody AccountRequest accountRequest) {
        AccountResponse account = accountService.createAccount(accountRequest);
        return account == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(account);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<AccountResponse> delete(@PathVariable("id") Long id) {
        AccountResponse deletedAccount = accountService.deleteAccount(id);
        return deletedAccount == null ? ResponseEntity.notFound().build() : ResponseEntity.accepted().body(deletedAccount);
    }

    @Override
    @PostMapping("/deposit")
    public ResponseEntity<MovementResponse> deposit(@RequestBody MovementRequest movementRequest) {
        MovementResponse movement = accountService.addBalance(movementRequest);
        return movement == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.accepted().body(movement);
    }

    @Override
    @PostMapping("/withdraw")
    public ResponseEntity<MovementResponse> withdraw(@RequestBody MovementRequest movementRequest) {
        MovementResponse movement = accountService.decreaseBalance(movementRequest);
        return movement == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.accepted().body(movement);
    }

}
