package com.unsa.bank.application.ports;

import com.unsa.bank.domain.dtos.UserResponse;
import org.springframework.web.servlet.function.EntityResponse;

import com.unsa.bank.domain.dtos.UserRequest;

import java.util.List;

public interface UserService {

    List<UserResponse> getAll();
    UserResponse getUserById(Long id);
    UserResponse getUserByDocument(String document);
    UserResponse getUserByEmailAndPassword(String email, String password);
    UserResponse saveUser(UserRequest userRequest);
    UserResponse updateUser(Long id, UserRequest userRequest);
    UserResponse deleteUser(Long id);

}
