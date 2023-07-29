package com.unsa.bank.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

    private Long id;
    private Long userId;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private Double balance;

}
