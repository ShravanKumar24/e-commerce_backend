package com.ecommerce.controllers;

import com.ecommerce.dtos.CartResponseDto;
import com.ecommerce.services.interfaces.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.dtos.ApiResponse;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private RestClient.Builder builder;


    @PostMapping("/add")
    public ResponseEntity<ApiResponse<CartResponseDto>> addToCart(Authentication auth, @RequestParam Long productId, @RequestParam Integer quantity) {
        return buildResponse(cartService.addItemsToCart(auth.getName(), productId, quantity), "Items added successfully");
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<CartResponseDto>> updateCartItem(Authentication auth, @RequestParam Long productId, @RequestParam Integer quantity) {
        return buildResponse(cartService.updateCartItem(auth.getName(), productId, quantity), "Items updated successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<CartResponseDto>> removeFromCart(Authentication auth, @RequestParam Long productId) {
        return buildResponse(cartService.removeItemFromCart(auth.getName(), productId), "Items removed successfully");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<CartResponseDto>> getCart(Authentication auth) {
        return buildResponse(cartService.getCartByUser(auth.getName()), "Cart fetched successfully");
    }

    @DeleteMapping("/clear")
    public ResponseEntity<ApiResponse<String>> clearCart(Authentication auth) {
        cartService.clearCart(auth.getName());
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Cart cleared")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }


    private <T> ResponseEntity<ApiResponse<T>> buildResponse(T data, String message) {
        return ResponseEntity.ok(
                ApiResponse.<T>builder()
                        .success(true)
                        .message(message)
                        .data(data)
                        .timestamp(LocalDateTime.now()).build()
        );
    }
}
