package com.ecommerce.services.interfaces;

import com.ecommerce.dtos.ApiResponse;
import com.ecommerce.dtos.OrderResponseDto;

import java.util.List;

public interface OrderService {

    OrderResponseDto placeOrder(String userName);

    List<OrderResponseDto> getUserOrder(String userName);

    OrderResponseDto getOrderById(Long orderId);

    String cancelOrder(Long orderId);

}
