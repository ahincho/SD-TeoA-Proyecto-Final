package com.unsa.users.infra;

import java.util.List;
import com.unsa.users.domain.entities.User;
import com.unsa.users.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> list() {
        List<User> users = userService.getAll();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id) {
        User recoveredUser = userService.getUserById(id);
        if (recoveredUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recoveredUser);
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") Long id) {
        User deletedUser = userService.deleteUser(id);
        if (deletedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deletedUser);
    }

}
