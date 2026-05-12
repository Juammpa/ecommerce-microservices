package com.juanmenoyo.productservice.service;

import com.juanmenoyo.productservice.dto.ProductRequestDto;
import com.juanmenoyo.productservice.dto.ProductResponseDto;
import com.juanmenoyo.productservice.mapper.ProductMapper;
import com.juanmenoyo.productservice.model.Product;
import com.juanmenoyo.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductResponseDto> findAll() {
        return productRepository.findAll().stream()
                .map(ProductMapper::toDto)
                .toList();
    }

    @Override
    public ProductResponseDto findById(Long id) {
        return productRepository.findById(id)
                .map(ProductMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }

    @Override
    public ProductResponseDto save(ProductRequestDto productRequestDto) {
        return ProductMapper.toDto(
                productRepository.save(
                        ProductMapper.toEntity(productRequestDto)));
    }


    @Override
    public ProductResponseDto update(Long id, ProductRequestDto productRequestDto) {
        return productRepository.findById(id)
                .map(existing -> {
                    existing.setName(productRequestDto.getName());
                    existing.setDescription(productRequestDto.getDescription());
                    existing.setPrice(productRequestDto.getPrice());
                    existing.setStock(productRequestDto.getStock());

                    return ProductMapper.toDto(productRepository.save(existing));
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

    }

    @Override
    public void deleteById(Long id) {

        productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        productRepository.deleteById(id);
    }


    @Override
    public void reduceStock(Long id, Integer quantity) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        if (product.getStock() < quantity) {
            throw new RuntimeException("Stock insuficiente");
        }
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
    }

}
