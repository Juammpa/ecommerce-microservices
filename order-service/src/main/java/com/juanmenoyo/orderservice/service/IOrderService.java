package com.juanmenoyo.orderservice.service;

import com.juanmenoyo.orderservice.dto.OrderRequestDto;
import com.juanmenoyo.orderservice.dto.OrderResponseDto;
import com.juanmenoyo.orderservice.dto.OrderStatusUpdateDto;

import java.util.List;

public interface IOrderService {

    List<OrderResponseDto> findAll();
    OrderResponseDto findById(Long id);
    OrderResponseDto save(OrderRequestDto orderRequestDto);
    OrderResponseDto updateStatus(Long id, OrderStatusUpdateDto orderStatusUpdateDto);
}
