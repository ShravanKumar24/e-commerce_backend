package com.ecommerce.dtos;

import com.ecommerce.entites.Cart;

import java.util.List;

public class CartResponseDto {

    private Long cartId;
    private String userEmail;
    private List<CartItemsResponseDto> cartItems;
    private double totalPrice;

    public CartResponseDto(Cart cart) {
        this.cartId = cart.getId();
        this.userEmail = cart.getUser().getEmail();
        this.cartItems = cart.getCartItems().stream().map(CartItemsResponseDto::new).toList();
        this.totalPrice=cart.getCartItems().stream().map(item -> item.getProduct().getPrice()).reduce(0.0, Double::sum);
    }
}
