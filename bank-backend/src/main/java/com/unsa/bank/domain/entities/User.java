package com.unsa.bank.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames =  {"document", "email"}) })
@Setter @Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String document;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Account> accounts = new HashSet<>();

    @Override
    public String toString() {
        return "User [id=" + id + ", firstname='" + firstname + "', lastname='" + lastname + "', accounts=" + accounts.toString() + "]";
    }

}
