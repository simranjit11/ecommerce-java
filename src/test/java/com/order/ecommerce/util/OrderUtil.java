package com.order.ecommerce.util;

import com.order.ecommerce.dto.AddressDto;
import com.order.ecommerce.dto.OrderDto;
import com.order.ecommerce.dto.OrderItemDto;
import com.order.ecommerce.enums.OrderStatus;
import com.order.ecommerce.enums.PaymentMode;
import com.order.ecommerce.enums.ShippingMode;
import com.order.ecommerce.model.Payment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderUtil {

    public static OrderDto createTestOrder() {
        return new OrderDto("1", 6.0, 10.0, 2.0, 2.0, "test", ShippingMode.DELIVERY, 10.0, PaymentMode.CREDIT, OrderStatus.PROCESSING, createAddress(), createAddress(), Arrays.asList(new OrderItemDto(), new OrderItemDto()));
    }


    public static AddressDto createAddress() {
        return new AddressDto("3755 M lane", "Apt 311", "Aurora", "IL", "60504", "test.gmail.com", "1234567890");
    }


    public static OrderDto createMockOrderResponse() {
        Payment payment = new Payment();
        AddressDto billingAddress = new AddressDto();
        AddressDto shippingAddress = new AddressDto();
        List mutableList = new ArrayList();
        LocalDateTime dateTime = LocalDateTime.parse("2022-10-17T11:31:27.771692");
        return new OrderDto("1", 6.0, 10.0, 2.0, 2.0, "testProduct", ShippingMode.DELIVERY, 10.0, PaymentMode.CREDIT, OrderStatus.PROCESSING, billingAddress, shippingAddress, mutableList);
    }
}
