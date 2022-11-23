package com.order.ecommerce.dto;

import com.order.ecommerce.enums.OrderStatus;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@Data
@ToString
@RequiredArgsConstructor
public class OrderCreateResponse {
    @NonNull String orderId;
    @NonNull OrderStatus orderStatus;
}
