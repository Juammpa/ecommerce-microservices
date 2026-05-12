package com.juanmenoyo.orderservice.dto;

import com.juanmenoyo.orderservice.model.enums.OrderStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderResponseDto {

    private Long id;
    private Long productId;
    private Integer quantity;
    private Double total;
    private OrderStatus status;
    private LocalDateTime createAt;
}
