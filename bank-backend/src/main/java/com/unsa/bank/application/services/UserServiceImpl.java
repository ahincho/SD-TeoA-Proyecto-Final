package com.unsa.bank.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import com.unsa.bank.application.ports.UserService;
import com.unsa.bank.domain.repositories.UserRepository;
import com.unsa.bank.domain.dtos.UserRequest;
import com.unsa.bank.domain.dtos.UserResponse;
import com.unsa.bank.domain.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.HashSet;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserResponse> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapUserToUserResponse).toList();
    }

    @Override
    public UserResponse getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(this::mapUserToUserResponse).orElse(null);
    }

    @Override
    public UserResponse getUserByDocument(String document) {
        Optional<User> optionalUser = userRepository.findByDocument(document);
        return optionalUser.map(this::mapUserToUserResponse).orElse(null);
    }

    @Override
    public UserResponse getUserByEmailAndPassword(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmailAndPassword(email, password);
        return optionalUser.map(this::mapUserToUserResponse).orElse(null);
    }

    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findByDocumentOrEmail(userRequest.getDocument(), userRequest.getEmail());
        if (optionalUser.isPresent()) { return null; }
        User savedUser = userRepository.save(mapUserRequestToUser(userRequest));
        savedUser.setAccounts(new HashSet<>());
        return mapUserToUserResponse(savedUser);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) { return null; }
        User userToUpdate = mapUserRequestToUser(userRequest);
        userToUpdate.setId(id);
        userToUpdate.setAccounts(optionalUser.get().getAccounts());
        return mapUserToUserResponse(userRepository.save(userToUpdate));
    }

    @Override
    public UserResponse deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return null;
        }
        User userToDelete = optionalUser.get();
        userRepository.delete(userToDelete);
        return mapUserToUserResponse(userToDelete);
    }

    private UserResponse mapUserToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .document(user.getDocument())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .accounts(user.getAccounts())
                .build();
    }

    private User mapUserRequestToUser(UserRequest userRequest) {
        return User.builder()
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .document(userRequest.getDocument())
                .firstname(userRequest.getFirstname())
                .lastname(userRequest.getLastname())
                .build();
    }

}
