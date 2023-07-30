package com.unsa.bank.application.ports;

import com.unsa.bank.domain.dtos.AccountRequest;
import com.unsa.bank.domain.dtos.AccountResponse;
import com.unsa.bank.domain.dtos.MovementRequest;
import com.unsa.bank.domain.dtos.MovementResponse;

import java.util.List;

public interface AccountService {

    List<AccountResponse> getAll();
    AccountResponse getAccountById(Long id);
    List<AccountResponse> getAccountsByUserId(AccountRequest accountRequest);
    AccountResponse createAccount(AccountRequest accountRequest);
    AccountResponse deleteAccount(Long id);
    MovementResponse addBalance(MovementRequest movementRequest);
    MovementResponse decreaseBalance(MovementRequest movementRequest);

}
