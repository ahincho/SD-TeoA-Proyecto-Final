package com.unsa.bank.application.ports;

import com.unsa.bank.domain.dtos.*;

import java.util.List;

public interface AccountService {

    List<AccountResponse> getAll();
    AccountResponse getAccountById(Long id);
    List<AccountResponse> getAccountsByUserId(AccountRequest accountRequest);
    List<AccountResponse> getAccountsByUserDocument(DocumentRequest documentRequest);
    AccountResponse createAccount(DocumentRequest documentRequest);
    AccountResponse deleteAccount(Long id);
    MovementResponse addBalance(MovementRequest movementRequest);
    MovementResponse decreaseBalance(MovementRequest movementRequest);

}
