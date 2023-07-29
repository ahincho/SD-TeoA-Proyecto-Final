package com.unsa.bank.application.ports;

import com.unsa.bank.domain.entities.User;

import java.util.List;

public interface UserService {

    List<User> getAll();
    User getUserById(Long id);
    User getUserByDocument(String document);
    User getUserByEmailAndPassword(String email, String password);
    User saveUser(User user);
    User updateUser(Long id, User user);
    User deleteUser(Long id);

}
