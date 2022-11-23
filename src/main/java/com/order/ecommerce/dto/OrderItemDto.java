package com.order.ecommerce.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString
@RequiredArgsConstructor
public class OrderItemDto {
    String productId;
    String quantity;
}
