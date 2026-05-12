package com.juanmenoyo.orderservice.controller;

import com.juanmenoyo.orderservice.dto.OrderRequestDto;
import com.juanmenoyo.orderservice.dto.OrderResponseDto;
import com.juanmenoyo.orderservice.dto.OrderStatusUpdateDto;
import com.juanmenoyo.orderservice.service.IOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> findAll() {

        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> findById(@PathVariable Long id) {

        return ResponseEntity.ok(orderService.findById(id));
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> save(@Valid @RequestBody OrderRequestDto request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(request));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderResponseDto> updateStatus(@PathVariable Long id,
                                                         @Valid @RequestBody OrderStatusUpdateDto request) {
        return ResponseEntity.ok(orderService.updateStatus(id, request));
    }

}
