package com.ecommerce.repositories;

import com.ecommerce.entites.Order;
import com.ecommerce.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);
}
