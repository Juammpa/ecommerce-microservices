package com.juanmenoyo.orderservice.dto;

import com.juanmenoyo.orderservice.model.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderStatusUpdateDto {

    @NotNull(message = "El estado no puede ser nulo.")
    private OrderStatus status;
}
