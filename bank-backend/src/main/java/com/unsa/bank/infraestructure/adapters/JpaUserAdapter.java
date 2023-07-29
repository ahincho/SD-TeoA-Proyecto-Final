package com.unsa.bank.infraestructure.adapters;

import org.springframework.http.ResponseEntity;

import com.unsa.bank.domain.dtos.UserRequest;
import com.unsa.bank.domain.dtos.UserResponse;

import java.util.List;

public interface JpaUserAdapter {

    ResponseEntity<List<UserResponse>> list();
    ResponseEntity<UserResponse> getById(Long id);
    ResponseEntity<UserResponse> getByDocument(String document);
    ResponseEntity<UserResponse> getByEmailAndPassword(String email, String password);
    ResponseEntity<UserResponse> save(UserRequest user);
    ResponseEntity<UserResponse> update(Long id, UserRequest user);
    ResponseEntity<UserResponse> delete(Long id);

}
