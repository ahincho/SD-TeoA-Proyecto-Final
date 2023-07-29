package com.unsa.bank.application.services;

import com.unsa.bank.domain.dtos.MovementRequest;
import com.unsa.bank.domain.dtos.MovementResponse;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import jakarta.transaction.Transactional;

import com.unsa.bank.application.ports.AccountService;
import com.unsa.bank.domain.repositories.AccountRepository;
import com.unsa.bank.domain.repositories.UserRepository;
import com.unsa.bank.domain.dtos.AccountRequest;
import com.unsa.bank.domain.dtos.AccountResponse;
import com.unsa.bank.domain.entities.Account;
import com.unsa.bank.domain.entities.User;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;
    UserRepository userRepository;

    @Override
    public List<AccountResponse> getAll() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(this::mapAccountToAccountResponse).toList();
    }

    @Override
    public AccountResponse getAccountById(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        return optionalAccount.map(this::mapAccountToAccountResponse).orElse(null);
    }

    @Override
    public AccountResponse createAccount(AccountRequest accountRequest) {
        Optional<User> optionalUser = userRepository.findById(accountRequest.getUserId());
        if (optionalUser.isEmpty()) {
            return null;
        }
        Account account = new Account();
        account.setUser(optionalUser.get());
        account.setBalance(0.0);
        return mapAccountToAccountResponse(accountRepository.save(account));
    }

    @Override
    public AccountResponse deleteAccount(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isEmpty()) {
            return null;
        }
        Account accountToDelete = optionalAccount.get();
        accountRepository.delete(accountToDelete);
        return mapAccountToAccountResponse(accountToDelete);
    }

    @Override
    public MovementResponse addBalance(MovementRequest movementRequest) {
        Optional<Account> optionalAccount = mapMovementRequestToAccount(movementRequest);
        if (optionalAccount.isEmpty() || movementRequest.getAmount() <= 0) {
            return null;
        }
        Account account = optionalAccount.get();
        Double amount = movementRequest.getAmount();
        account.setBalance(account.getBalance() + amount);
        Account addedBalaceAccount = accountRepository.save(account);
        return mapAccountToMovementResponse(addedBalaceAccount, amount);
    }

    @Override
    public MovementResponse decreaseBalance(MovementRequest movementRequest) {
        Optional<Account> optionalAccount = mapMovementRequestToAccount(movementRequest);
        if (optionalAccount.isEmpty()) {
            return null;
        }
        Account account = optionalAccount.get();
        Double amount = movementRequest.getAmount();
        if (amount <= 0 || amount > account.getBalance()) {
            return null;
        }
        account.setBalance(account.getBalance() - amount);
        Account decreasedBalanceAccount = accountRepository.save(account);
        return mapAccountToMovementResponse(decreasedBalanceAccount, amount * -1);
    }

    private AccountResponse mapAccountToAccountResponse(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .userId(account.getUser().getId())
                .creationDate(account.getCreationDate())
                .updateDate(account.getUpdateDate())
                .balance(account.getBalance())
                .build();
    }

    private MovementResponse mapAccountToMovementResponse(Account account, Double amount) {
        return MovementResponse.builder()
                .accountId(account.getId())
                .balance(account.getBalance())
                .amount(amount)
                .build();
    }

    private Optional<Account> mapMovementRequestToAccount(MovementRequest movementRequest) {
        return accountRepository.findById(movementRequest.getAccountId());
    }

}
