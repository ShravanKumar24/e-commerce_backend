package com.ecommerce.dtos;

import com.ecommerce.entites.Order;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDto {

    private Long orderId;
    private String userName;
    private LocalDateTime orderDate;
    private List<OrderItemsResponseDto> orderItems;
    private double totalPrice;

    public OrderResponseDto(Order order) {
        this.orderId = order.getId();
        this.userName = order.getUser().getEmail();
        this.orderDate = order.getOrderDate();
        this.orderItems = order.getOrderItems().stream().map(OrderItemsResponseDto::new).toList();
        this.totalPrice = order.getOrderItems().stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum();

    }


}
