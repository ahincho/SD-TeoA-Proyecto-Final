package com.unsa.bank.infraestructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.unsa.bank.infraestructure.adapters.JpaUserAdapter;
import com.unsa.bank.application.ports.UserService;
import com.unsa.bank.domain.entities.User;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController implements JpaUserAdapter {

    @Autowired
    UserService userService;

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> list() {
        return userService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @Override
    @GetMapping("/d/{doc}")
    @ResponseStatus(HttpStatus.OK)
    public User getByDocument(@PathVariable("doc") String document) {
        return userService.getUserByDocument(document);
    }

    @Override
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public User getByEmailAndPassword(@RequestBody String email, @RequestBody String password) {
        return userService.getUserByEmailAndPassword(email, password);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User delete(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

}
