package com.juanmenoyo.orderservice.client;

import com.juanmenoyo.orderservice.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ProductClient {

    private final RestTemplate restTemplate;

    private final String PRODUCT_URL = "http://localhost:8081/api/products";

    public ProductResponseDto getProduct(Long id) {
        return restTemplate.getForObject(PRODUCT_URL + "/" + id, ProductResponseDto.class);
    }

    public void reduceStock(Long id, Integer quantity) {
        restTemplate.put(PRODUCT_URL + "/" + id + "/reduce-stock?quantity=" + quantity, null);
    }

}
