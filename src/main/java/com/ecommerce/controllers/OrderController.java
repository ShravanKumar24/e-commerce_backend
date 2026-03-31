package com.ecommerce.controllers;

import com.ecommerce.dtos.ApiResponse;
import com.ecommerce.dtos.OrderResponseDto;
import com.ecommerce.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponseDto>> placesOrder(Authentication auth) {
        return buildResponse(orderService.placeOrder(auth.getName()), "Order place Successfully");
    }

    @GetMapping("{orderId}")
    public ResponseEntity<ApiResponse<OrderResponseDto>> getOrderById(@PathVariable Long orderid) {
        return buildResponse(orderService.getOrderById(orderid), "Order fetched successfully");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponseDto>>> getUserOrders(Authentication auth) {
        return buildResponse(orderService.getUserOrder(auth.getName()), "Orders fetched Successfully");
    }

    @DeleteMapping("/cancel/{orderId}")
    public ResponseEntity<ApiResponse<List<OrderResponseDto>>> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return buildResponse(null, "Order cancelled successfully");
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
