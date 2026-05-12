package com.juanmenoyo.orderservice.model;

import com.juanmenoyo.orderservice.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double total;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    // Se ejecuta automaticamente antes de guardar la orden
    // Asigna fecha actual y estado inicial
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
    }

}
