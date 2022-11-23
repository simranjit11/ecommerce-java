package com.order.ecommerce.controller;

import com.order.ecommerce.dto.ProductDto;
import com.order.ecommerce.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/v1"})
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping({"/products"})
    @Operation(
            summary = "Create a product",
            description = "Create a product"
    )
    public ResponseEntity<ProductDto> createOrder(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.OK);
    }

    @GetMapping({"/products/{productId}"})
    @Operation(
            summary = "Get a product",
            description = "Get a product"
    )
    public ResponseEntity<ProductDto> getProduct(@PathVariable(name = "productId") String productId) {
        return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
    }

}
