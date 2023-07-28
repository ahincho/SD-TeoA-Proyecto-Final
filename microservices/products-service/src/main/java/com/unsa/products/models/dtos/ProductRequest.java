package com.unsa.products.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private String sku;
    private String name;
    private String description;
    private Double price;
    private Boolean status;

}
