package com.juanmenoyo.productservice.mapper;

import com.juanmenoyo.productservice.dto.ProductRequestDto;
import com.juanmenoyo.productservice.dto.ProductResponseDto;
import com.juanmenoyo.productservice.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public static Product toEntity(ProductRequestDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        return product;
    }

    public static ProductResponseDto toDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        return dto;
    }

}
