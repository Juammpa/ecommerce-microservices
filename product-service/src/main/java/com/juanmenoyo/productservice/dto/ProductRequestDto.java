package com.juanmenoyo.productservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {

    @NotBlank(message = "El nombre no puede estar vacio.")
    private String name;

    private String description;

    @NotNull(message = "El precio no puede ser nulo.")
    @Positive(message = "El precio debe ser mayor a 0")
    private Double price;


    @NotNull(message = "El stock no puede ser nulo.")
    @PositiveOrZero(message = "El stock no puede ser negativo.")
    private Integer stock;


}
