package com.ecommerce.repositories;

import com.ecommerce.entites.Cart;
import com.ecommerce.entites.CartItem;
import com.ecommerce.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemsRepo extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
}
