package com.unsa.orders.services;

import com.unsa.orders.models.dtos.OrderItemRequest;
import com.unsa.orders.models.dtos.OrderRequest;
import com.unsa.orders.models.entities.Order;
import com.unsa.orders.models.entities.OrderItems;
import com.unsa.orders.models.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        // Check for Inventory
        Order order = new Order();
        order.setCode(UUID.randomUUID().toString());
        order.setOrderItems(orderRequest.getOrderItems().stream()
                .map(orderItemRequest -> mapOrderItemRequestToOrderItem(orderItemRequest, order))
                .toList()
        );
        orderRepository.save(order);
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
