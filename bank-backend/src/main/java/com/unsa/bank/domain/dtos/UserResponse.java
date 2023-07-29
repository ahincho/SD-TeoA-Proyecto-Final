package com.unsa.bank.domain.dtos;

import lombok.*;

import com.unsa.bank.domain.entities.Account;

import java.util.Set;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String document;
    private String firstname;
    private String lastname;
    private Set<Account> accounts;

}
