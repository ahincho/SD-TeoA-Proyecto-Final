package com.unsa.bank.infraestructure.adapters;

import com.unsa.bank.domain.entities.User;
import java.util.List;

public interface JpaUserAdapter {

    List<User> list();
    User getById();
    User save(User user);
    User update(Long id, User user);
    User delete(Long id);

}
