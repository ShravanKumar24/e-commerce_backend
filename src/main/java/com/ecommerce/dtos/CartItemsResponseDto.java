package com.ecommerce.dtos;

import com.ecommerce.entites.CartItem;
import lombok.Data;

@Data
public class CartItemsResponseDto {
    private Long productId;
    private String productName;
    private double price;
    private int quantity;
    private double totalPrice;

    public CartItemsResponseDto(CartItem cartItems) {
        this.productId = cartItems.getProduct().getId();
        this.productName = cartItems.getProduct().getProductName();
        this.price = cartItems.getProduct().getPrice();
        this.quantity = cartItems.getQuantity();
        this.totalPrice = cartItems.getProduct().getPrice() * cartItems.getQuantity();
    }
}
