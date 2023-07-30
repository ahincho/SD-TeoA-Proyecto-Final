package com.unsa.bank.config;

import com.unsa.bank.domain.entities.Account;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

import com.unsa.bank.domain.repositories.AccountRepository;
import com.unsa.bank.domain.repositories.UserRepository;
import com.unsa.bank.domain.entities.User;

import java.util.List;

@Component
@AllArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private UserRepository userRepository;
    private AccountRepository accountRepository;

    @Override
    public void run(String[] args) throws Exception {
        User ahincho = User.builder().email("ahincho@unsa.edu.pe").password("ahincho").document("123456789").firstname("Angel Eduardo").lastname("Hincho Jove").build();
        User ftapara = User.builder().email("ftapara@unsa.edu.pe").password("ftapara").document("987654321").firstname("Fabiola Grissel").lastname("Tapara Quispe").build();
        User dneira = User.builder().email("dneira@unsa.edu.pe").password("dneira").document("456789123").firstname("Darwin Jesus").lastname("Neira Carrasco").build();
        User ntacca = User.builder().email("ntacca@unsa.edu.pe").password("ntacca").document("321654987").firstname("Nohelia Estephania").lastname("Tacca Apaza").build();
        User schavez = User.builder().email("schavez@unsa.edu.pe").password("schavez").document("987123654").firstname("Samir Diego").lastname("Chavez Caceres").build();
        List<User> users = List.of(ahincho, ftapara, dneira, ntacca, schavez);
        userRepository.saveAll(users);
        for (User user : users) {
            for (int i = 0 ; i < 3 ; i++) {
                accountRepository.save(Account.builder().user(user).balance(0.0).build());
            }
        }
    }

}
