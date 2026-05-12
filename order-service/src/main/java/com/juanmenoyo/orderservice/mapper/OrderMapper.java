package com.juanmenoyo.orderservice.mapper;

import com.juanmenoyo.orderservice.dto.OrderRequestDto;
import com.juanmenoyo.orderservice.dto.OrderResponseDto;
import com.juanmenoyo.orderservice.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public static Order toEntity(OrderRequestDto dto) {
        Order order = new Order();
        order.setProductId(dto.getProductId());
        order.setQuantity(dto.getQuantity());
        return order;
    }

    public static OrderResponseDto toDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setProductId(order.getProductId());
        dto.setQuantity(order.getQuantity());
        dto.setTotal(order.getTotal());
        dto.setStatus(order.getStatus());
        dto.setCreateAt(order.getCreatedAt());
        return dto;
    }

}
