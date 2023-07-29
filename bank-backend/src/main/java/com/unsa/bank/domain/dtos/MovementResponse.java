package com.unsa.bank.domain.dtos;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovementResponse {

    private Long accountId;
    private Double balance;
    private Double amount;

}
