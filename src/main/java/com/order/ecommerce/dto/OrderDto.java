package com.order.ecommerce.dto;

import com.order.ecommerce.enums.OrderStatus;
import com.order.ecommerce.enums.PaymentMode;
import com.order.ecommerce.enums.ShippingMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    @NonNull String customerId;
    @NonNull Double subTotal;
    @NonNull Double totalAmt;
    @NonNull Double tax;
    @NonNull Double shippingCharges;
    @NonNull String title;
    @NonNull ShippingMode shippingMode;

    @NonNull Double amount;
    @NonNull PaymentMode paymentMode;

    OrderStatus orderStatus;

    @NonNull AddressDto billingAddress;
    @NonNull AddressDto shippingAddress;

    @NonNull List<OrderItemDto> orderItems;
}
