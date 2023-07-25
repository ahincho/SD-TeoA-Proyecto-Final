package com.unsa.users.application.services;

import java.util.List;
import com.unsa.users.domain.entities.User;

public interface UserService {

    List<User> getAll();
    User getUserById(Long id);
    User saveUser(User user);
    User updateUser(Long id, User user);
    User deleteUser(Long id);

}
