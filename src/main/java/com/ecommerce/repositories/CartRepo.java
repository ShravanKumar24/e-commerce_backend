package com.ecommerce.repositories;

import com.ecommerce.entites.Cart;
import com.ecommerce.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUser(User user);
}
