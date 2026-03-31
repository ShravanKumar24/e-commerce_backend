package com.ecommerce.dtos;

import com.ecommerce.entites.OrderItem;
import lombok.Data;

@Data
public class OrderItemsResponseDto {
    private Long productId;
    private String productName;
    private double price;
    private int quantity;
    private double totalPrice;

    public OrderItemsResponseDto(OrderItem orderItems) {
        this.productId=orderItems.getProduct().getId();
        this.productName=orderItems.getProduct().getProductName();
        this.price=orderItems.getProduct().getPrice();
        this.quantity=orderItems.getQuantity();
        this.totalPrice=orderItems.getPrice()*orderItems.getQuantity();
    }

}
