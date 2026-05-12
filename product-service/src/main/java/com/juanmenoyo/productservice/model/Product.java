package com.juanmenoyo.productservice.model;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Double price;


    @Column(nullable = false)
    private Integer stock;

}
