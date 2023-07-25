package com.unsa.users.application.services;

import java.util.List;
import java.util.Optional;

import com.unsa.users.domain.entities.User;
import com.unsa.users.domain.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(Long id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return null;
        }
        User userToUpdate = optionalUser.get();
        userToUpdate.setFirstname(user.getFirstname());
        userToUpdate.setLastname(user.getLastname());
        return userRepository.save(userToUpdate);
    }

    @Override
    public User deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return null;
        }
        User userToDelete = optionalUser.get();
        userRepository.delete(userToDelete);
        return userToDelete;
    }

}
