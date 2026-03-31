package com.ecommerce.services;

import com.ecommerce.dtos.CartResponseDto;
import com.ecommerce.entites.Cart;
import com.ecommerce.entites.CartItem;
import com.ecommerce.entites.Product;
import com.ecommerce.entites.User;
import com.ecommerce.repositories.*;
import com.ecommerce.services.interfaces.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CartItemsRepo cartItemsRepo;

    @Override
    public CartResponseDto getCartByUser(String userName) {

        User user = userRepo.findByEmail(userName)
                .orElseThrow(() -> new RuntimeException("User not found!"));


        Cart cart = cartRepo.findByUser(user).orElseThrow(() -> new RuntimeException("Cart not found!"));

        return new CartResponseDto(cart);
    }

    @Override
    public CartResponseDto addItemsToCart(String userName, Long productId, Integer quantity) {
        User user = userRepo.findByEmail(userName).orElseThrow(() -> new RuntimeException("User not found!"));
        Product product = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found!"));

        Cart cart = cartRepo.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepo.save(newCart);
        });

        CartItem cartItem = cartItemsRepo.findByCartAndProduct(cart, product).orElse(null);
        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItem = new CartItem();
            cartItem.setQuantity(quantity);
            cartItem.setProduct(product);
            cartItem.setCart(cart);
        }
        cartItemsRepo.save(cartItem);

        return new CartResponseDto(cart);
    }

    @Override
    public CartResponseDto updateCartItem(String userName, Long productId, Integer quantity) {
        User user = userRepo.findByEmail(userName).orElseThrow(() -> new RuntimeException("User not found!"));
        Cart cart = cartRepo.findByUser(user).orElseThrow(() -> new RuntimeException("Cart not found!"));
        Product product = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found!"));
        CartItem cartItem = cartItemsRepo.findByCartAndProduct(cart, product).orElseThrow(() -> new RuntimeException("CartItem not found!"));
        cartItem.setQuantity(quantity);
        cartItemsRepo.save(cartItem);

        return new CartResponseDto(cart);
    }

    @Override
    public CartResponseDto removeItemFromCart(String userName, Long productId) {
        User user = userRepo.findByEmail(userName).orElseThrow(() -> new RuntimeException("User not found!"));
        Cart cart = cartRepo.findByUser(user).orElseThrow(() -> new RuntimeException("Cart not found!"));
        Product product = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found!"));
        CartItem cartItem = cartItemsRepo.findByCartAndProduct(cart, product).orElseThrow(() -> new RuntimeException("CartItem not found!"));

        cartItemsRepo.delete(cartItem);
        return new CartResponseDto(cart);
    }

    @Override
    public String clearCart(String userName) {
        User user = userRepo.findByEmail(userName).orElseThrow(() -> new RuntimeException("User not found!"));
        Cart cart = cartRepo.findByUser(user).orElseThrow(() -> new RuntimeException("Cart not found!"));
        cart.getCartItems().clear();
        return "Successfully Cart cleared";
    }

}
