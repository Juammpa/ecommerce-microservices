package com.juanmenoyo.productservice.controller;

import com.juanmenoyo.productservice.dto.ProductRequestDto;
import com.juanmenoyo.productservice.dto.ProductResponseDto;
import com.juanmenoyo.productservice.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAll() {

        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable Long id) {

        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> save(@Valid @RequestBody ProductRequestDto request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update(@PathVariable Long id,
                                                     @Valid @RequestBody ProductRequestDto request) {
        return ResponseEntity.ok(productService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/reduce-stock")
    public ResponseEntity<Void> reduceStock(@PathVariable Long id,
                                            @RequestParam Integer quantity) {
        productService.reduceStock(id, quantity);
        return ResponseEntity.ok().build();
    }


}
