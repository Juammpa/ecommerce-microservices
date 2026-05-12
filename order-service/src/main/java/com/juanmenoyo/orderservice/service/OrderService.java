package com.juanmenoyo.orderservice.service;

import com.juanmenoyo.orderservice.client.ProductClient;
import com.juanmenoyo.orderservice.dto.OrderRequestDto;
import com.juanmenoyo.orderservice.dto.OrderResponseDto;
import com.juanmenoyo.orderservice.dto.OrderStatusUpdateDto;
import com.juanmenoyo.orderservice.dto.ProductResponseDto;
import com.juanmenoyo.orderservice.mapper.OrderMapper;
import com.juanmenoyo.orderservice.model.Order;
import com.juanmenoyo.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductClient productClient;


    @Override
    public List<OrderResponseDto> findAll() {
        return orderRepository.findAll().stream()
                .map(OrderMapper::toDto)
                .toList();
    }

    @Override
    public OrderResponseDto findById(Long id) {
        return orderRepository.findById(id)
                .map(OrderMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada con id: " + id));
    }

    @Override
    public OrderResponseDto save(OrderRequestDto orderRequestDto) {

        // 1. Le pregunta al product-service si el producto existe
        ProductResponseDto product = productClient.getProduct(orderRequestDto.getProductId());

        // 2. Valida si hay stock suficiente
        if(product.getStock() < orderRequestDto.getQuantity()) {
            throw new RuntimeException(
                "Stock insuficiente para el producto id: " + orderRequestDto.getProductId());
        }

        // 3. Calcula el total
        Order order = OrderMapper.toEntity(orderRequestDto);
        order.setTotal(product.getPrice() * orderRequestDto.getQuantity());

        // 4. Guarda la orden
        Order savedOrder = orderRepository.save(order);

        // 5. Le avisa al product-service que descuente el stock
        productClient.reduceStock(orderRequestDto.getProductId(), orderRequestDto.getQuantity());

        return OrderMapper.toDto(savedOrder);

    }

    @Override
    public OrderResponseDto updateStatus(Long id, OrderStatusUpdateDto orderStatusUpdateDto) {
        return orderRepository.findById(id)
                .map(existing -> {
                    existing.setStatus(orderStatusUpdateDto.getStatus());
                    return OrderMapper.toDto(orderRepository.save(existing));
                })
                .orElseThrow(()-> new RuntimeException("Orden no encontrada con id: " + id));
    }

}

