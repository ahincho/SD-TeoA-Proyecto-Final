package com.unsa.orders.services;

import com.unsa.orders.models.dtos.BaseResponse;
import com.unsa.orders.models.dtos.OrderItemRequest;
import com.unsa.orders.models.dtos.OrderRequest;
import com.unsa.orders.models.entities.Order;
import com.unsa.orders.models.entities.OrderItems;
import com.unsa.orders.models.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest orderRequest) {
        // Check for Inventory, We need to use a HTTP Client to consume Inventory API
        BaseResponse result = webClientBuilder.build()
                .post()
                .uri("http://localhost:8085/api/inventory/in-stock")
                .bodyValue(orderRequest.getOrderItems())
                .retrieve()
                .bodyToMono(BaseResponse.class)
                .block();
        if (result != null && !result.hasErrors()) {
            Order order = new Order();
            order.setCode(UUID.randomUUID().toString());
            order.setOrderItems(orderRequest.getOrderItems().stream()
                    .map(orderItemRequest -> mapOrderItemRequestToOrderItem(orderItemRequest, order))
                    .toList()
            );
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Some of the products are not in stock.");
        }
    }

    private OrderItems mapOrderItemRequestToOrderItem(OrderItemRequest orderItemRequest, Order order) {
        return OrderItems.builder()
                .id(orderItemRequest.getId())
                .sku(orderItemRequest.getSku())
                .price(orderItemRequest.getPrice())
                .quantity(orderItemRequest.getQuantity())
                .order(order)
                .build();
    }

}
