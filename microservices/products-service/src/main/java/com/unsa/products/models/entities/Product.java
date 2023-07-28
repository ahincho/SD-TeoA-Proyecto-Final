package com.unsa.products.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Setter @Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sku;
    private String name;
    private String description;
    private Double price;
    private Boolean status;

    @Override
    public String toString() {
        return "Product [id=" + id + ", sku='" + sku + "', name='" + name + "', description='" + description + "', price=" + price + ", status=" + status + "]";
    }

}
