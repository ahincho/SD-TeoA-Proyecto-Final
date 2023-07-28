package com.unsa.bank.infraestructure.controllers;

import com.unsa.bank.application.ports.UserService;
import com.unsa.bank.domain.entities.User;
import com.unsa.bank.infraestructure.adapters.JpaUserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @Override
    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User update(Long id, User user) {
        return userService.updateUser(id, user);
    }

    @Override
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public User delete(Long id) {
        return userService.deleteUser(id);
    }

}
