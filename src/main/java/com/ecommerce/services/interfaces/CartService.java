package com.ecommerce.services.interfaces;

import com.ecommerce.dtos.ApiResponse;
import com.ecommerce.dtos.CartResponseDto;

public interface CartService {

    CartResponseDto getCartByUser(String userName);

    CartResponseDto addItemsToCart(String userName, Long productId, Integer quantity);

    CartResponseDto updateCartItem(String userName, Long productId, Integer quantity);

    CartResponseDto removeItemFromCart(String userName, Long productId);

    String clearCart(String userName);

}
