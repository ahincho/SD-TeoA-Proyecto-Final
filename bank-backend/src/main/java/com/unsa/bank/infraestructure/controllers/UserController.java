package com.unsa.bank.infraestructure.controllers;

import com.unsa.bank.domain.dtos.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.unsa.bank.infraestructure.adapters.JpaUserAdapter;
import com.unsa.bank.application.ports.UserService;
import com.unsa.bank.domain.dtos.UserRequest;
import com.unsa.bank.domain.dtos.UserResponse;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController implements JpaUserAdapter {

    @Autowired
    UserService userService;

    @Override
    @GetMapping
    public ResponseEntity<List<UserResponse>> list() {
        List<UserResponse> users = userService.getAll();
        return users.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(users);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable("id") Long id) {
        UserResponse user = userService.getUserById(id);
        return user == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(user);
    }

    @Override
    @GetMapping("/d/{doc}")
    public ResponseEntity<UserResponse> getByDocument(@PathVariable("doc") String document) {
        UserResponse user = userService.getUserByDocument(document);
        return user == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(user);
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<UserResponse> getByEmailAndPassword(@RequestBody LoginRequest loginRequest) {
        UserResponse user = userService.getUserByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        return user == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(user);
    }

    @Override
    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody UserRequest user) {
        UserResponse createdUser  = userService.saveUser(user);
        if (createdUser == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdUser);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody UserRequest user) {
        UserResponse updatedUser = userService.updateUser(id, user);
        return updatedUser == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updatedUser);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> delete(@PathVariable Long id) {
        UserResponse deletedUser = userService.deleteUser(id);
        return deletedUser == null ? ResponseEntity.notFound().build() : ResponseEntity.accepted().body(deletedUser);
    }

}
