package com.unsa.bank.domain.entities;

import jakarta.persistence.*;

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
    private String email;
    private String password;
    private String document;
    private String firstname;
    private String lastname;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Account> accounts = new HashSet<>();

    @Override
    public String toString() {
        return "User [id=" + id + ", firstname='" + firstname + "', lastname='" + lastname + "', accounts=" + accounts.toString() + "]";
    }

}
