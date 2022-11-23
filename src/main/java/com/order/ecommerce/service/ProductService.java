package com.order.ecommerce.service;

import com.order.ecommerce.dto.ProductDto;
import com.order.ecommerce.exception.ProductNotFoundException;
import com.order.ecommerce.model.Product;
import com.order.ecommerce.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);


    @Autowired
    private ProductRepository productRepository;

    public ProductDto createProduct(ProductDto productDto) {
        log.info("Creating Product with productId {}", productDto.getProductId());
        Product product = productRepository.save(toProductEntity(productDto));
        return toProductDto(product);
    }

    public ProductDto getProduct(String productId) {
        log.info("Get Product by productId {}", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("EXEC", String.format("No product found with productId %s", productId)));
        return toProductDto(product);
    }

    private ProductDto toProductDto(Product product) {
        return ProductDto.builder().productId(product.getProductId())
                .description(product.getDescription())
                .sku(product.getSku())
                .price(product.getPrice())
                .title(product.getTitle())
                .createdAt(product.getCreatedAt())
                .build();
    }

    private Product toProductEntity(ProductDto productDto) {
        return Product.builder()
                .productId(productDto.getProductId())
                .sku(productDto.getSku())
                .title(productDto.getTitle())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .createdAt(LocalDate.now())
                .orderItems(null)
                .build();
    }

}
