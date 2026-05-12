package com.juanmenoyo.productservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
}
