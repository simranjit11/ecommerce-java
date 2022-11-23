package com.order.ecommerce.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@RequiredArgsConstructor
@Builder
public class ProductDto {
    @NonNull String productId;
    @NonNull String sku;
    @NonNull String title;
    @NonNull String description;
    @NonNull Double price;
    @NonNull LocalDate createdAt;
}
