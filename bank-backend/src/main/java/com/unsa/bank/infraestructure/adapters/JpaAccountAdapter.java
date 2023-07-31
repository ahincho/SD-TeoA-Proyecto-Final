package com.unsa.bank.infraestructure.adapters;

import org.springframework.http.ResponseEntity;

import com.unsa.bank.domain.dtos.*;

import java.util.List;

public interface JpaAccountAdapter {

    ResponseEntity<List<AccountResponse>> list();
    ResponseEntity<AccountResponse> getById(Long id);
    ResponseEntity<List<AccountResponse>> getByUserId(AccountRequest accountRequest);
    ResponseEntity<List<AccountResponse>> getByUserDocument(DocumentRequest documentRequest);
    ResponseEntity<AccountResponse> create(DocumentRequest documentRequest);
    ResponseEntity<AccountResponse> delete(Long id);
    ResponseEntity<MovementResponse> deposit(MovementRequest movementRequest);
    ResponseEntity<MovementResponse> withdraw(MovementRequest movementRequest);

}
