package com.unsa.products.services;

import com.unsa.products.models.dtos.ProductRequest;
import com.unsa.products.models.dtos.ProductResponse;
import com.unsa.products.models.entities.Product;
import com.unsa.products.models.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void addProduct(ProductRequest productRequest) {
        var product = Product.builder()
                .sku(productRequest.getSku())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .status(productRequest.getStatus())
                .build();
        productRepository.save(product);
        log.info("Producto Added: {}", product);
    }

    public List<ProductResponse> getAllProducts() {
        var products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .status(product.getStatus())
                .build();
    }

}
