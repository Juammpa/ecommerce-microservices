package com.juanmenoyo.productservice.service;

import com.juanmenoyo.productservice.dto.ProductRequestDto;
import com.juanmenoyo.productservice.dto.ProductResponseDto;

import java.util.List;

public interface IProductService {

    List<ProductResponseDto> findAll();
    ProductResponseDto findById(Long id);
    ProductResponseDto save(ProductRequestDto productRequestDto);
    ProductResponseDto update(Long id, ProductRequestDto productRequestDto);
    void deleteById(Long id);

    void reduceStock(Long id, Integer quantity);

}
