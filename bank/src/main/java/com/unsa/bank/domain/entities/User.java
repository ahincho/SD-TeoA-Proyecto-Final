package com.unsa.bank.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Setter @Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;

    @Override
    public String toString() {
        return "User [id=" + id + ", firstname='" + firstname + "', lastname='" + lastname + "']";
    }

}
