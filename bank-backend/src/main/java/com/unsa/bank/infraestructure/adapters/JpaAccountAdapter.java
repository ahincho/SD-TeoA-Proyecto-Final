package com.unsa.bank.infraestructure.adapters;

import org.springframework.http.ResponseEntity;

import com.unsa.bank.domain.dtos.AccountRequest;
import com.unsa.bank.domain.dtos.AccountResponse;
import com.unsa.bank.domain.dtos.MovementRequest;
import com.unsa.bank.domain.dtos.MovementResponse;

import java.util.List;

public interface JpaAccountAdapter {

    ResponseEntity<List<AccountResponse>> list();
    ResponseEntity<AccountResponse> getById(Long id);
    ResponseEntity<AccountResponse> create(AccountRequest accountRequest);
    ResponseEntity<AccountResponse> delete(Long id);
    ResponseEntity<MovementResponse> deposit(MovementRequest movementRequest);
    ResponseEntity<MovementResponse> withdraw(MovementRequest movementRequest);

}
