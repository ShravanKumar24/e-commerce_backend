package com.ecommerce.services;

import com.ecommerce.dtos.OrderResponseDto;
import com.ecommerce.entites.*;
import com.ecommerce.repositories.CartRepo;
import com.ecommerce.repositories.OrderRepo;
import com.ecommerce.repositories.UserRepo;
import com.ecommerce.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private OrderRepo orderRepo;


    @Override
    public OrderResponseDto placeOrder(String userName) {

        User user = userRepo.findByEmail(userName).orElseThrow(() -> new RuntimeException("User not found!"));
        Cart cart = cartRepo.findByUser(user).orElseThrow(() -> new RuntimeException("Cart not found!"));

        if (cart.getCartItems().isEmpty()) {
            throw new RuntimeException("Cart is empty!");
        }

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.PLACED);
        order.setPaymentStatus(PaymentStatus.PENDING);

        List<OrderItem> orderItems = cart.getCartItems().stream().map(cartItem -> {

            Product product = cartItem.getProduct();
            if (product.getQuantity() <= cartItem.getQuantity()) {
                throw new RuntimeException("Product quantity less than quantity!" + product.getProductName());
            }

            product.setQuantity(product.getQuantity() - cartItem.getQuantity());
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(product.getPrice());
            return orderItem;
        }).toList();
        order.setOrderItems(orderItems);
        double total = orderItems.stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum();
        order.setTotalPrice(total);
        Order savedOrder = orderRepo.save(order);
        cart.getCartItems().clear();
        cartRepo.save(cart);
        return new OrderResponseDto(savedOrder);
    }

    @Override
    public List<OrderResponseDto> getUserOrder(String userName) {
        User user = userRepo.findByEmail(userName).orElseThrow(() -> new RuntimeException("User not found!"));

        return orderRepo.findByUser(user).stream().map(OrderResponseDto::new).toList();
    }

    @Override
    public OrderResponseDto getOrderById(Long orderId) {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found!"));
        return new OrderResponseDto(order);
    }

    @Override
    public String cancelOrder(Long orderId) {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found!"));
        order.setOrderStatus(OrderStatus.CANCELLED);
        orderRepo.save(order);
        return "Order has been cancelled Successfully!";
    }
}
