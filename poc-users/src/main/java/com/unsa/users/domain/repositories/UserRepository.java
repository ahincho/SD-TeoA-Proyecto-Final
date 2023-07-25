package com.unsa.users.domain.repositories;

import com.unsa.users.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
