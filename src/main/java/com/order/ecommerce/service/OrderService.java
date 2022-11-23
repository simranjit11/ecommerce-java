package com.order.ecommerce.service;

import com.order.ecommerce.dto.AddressDto;
import com.order.ecommerce.dto.OrderCreateResponse;
import com.order.ecommerce.dto.OrderDto;
import com.order.ecommerce.dto.OrderItemDto;
import com.order.ecommerce.enums.OrderStatus;
import com.order.ecommerce.exception.OrderNotFoundException;
import com.order.ecommerce.mapper.OrderDetailsMapper;
import com.order.ecommerce.model.Address;
import com.order.ecommerce.model.Order;
import com.order.ecommerce.model.OrderItem;
import com.order.ecommerce.repository.OrderItemRepository;
import com.order.ecommerce.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailsMapper orderDetailsMapper;

    @Autowired
    private OrderItemRepository orderItemRepository;


    public void updateOrderStatus(String orderId, OrderStatus orderStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("EXEC", String.format("Order not found with orderId %s", orderId)));
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
    }

    public OrderDto findOrderById(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("EXEC", String.format("Order not found with orderId %s", orderId)));
        return toOrderDto(order);
    }


    @Transactional
    public OrderCreateResponse createOrder(OrderDto orderDto) {
        log.info("Creating Order for customer {}", orderDto.getCustomerId());
        Order savedOrder = orderRepository.save(toOrderEntity(orderDto, UUID.randomUUID().toString()));

        List<OrderItem> orderItemList = orderDetailsMapper.buildOrderItems(orderDto.getOrderItems(), savedOrder.getOrderId());
        orderItemRepository.saveAll(orderItemList);

        return new OrderCreateResponse(savedOrder.getOrderId(), savedOrder.getOrderStatus());
    }

    private Order toOrderEntity(OrderDto orderDto, String orderId) {
        return Order.builder()
                .orderId(orderId)
                .orderStatus(OrderStatus.PROCESSING)
                .customerId(orderDto.getCustomerId())
                .subTotal(orderDto.getSubTotal())
                .totalAmt(orderDto.getTotalAmt())
                .tax(orderDto.getTax())
                .shippingCharges(orderDto.getShippingCharges())
                .title(orderDto.getTitle())
                .shippingMode(orderDto.getShippingMode())
                .createdAt(LocalDateTime.now())
                .payment(orderDetailsMapper.buildAndLoadPayment(orderDto.getAmount(), orderDto.getPaymentMode()))
                .billingAddress(orderDetailsMapper.buildAndLoadAddress(orderDto.getBillingAddress()))
                .shippingAddress(orderDetailsMapper.buildAndLoadAddress(orderDto.getShippingAddress()))
                .build();
    }

    private OrderDto toOrderDto(Order order) {
        return OrderDto.builder()
                .customerId(order.getCustomerId())
                .billingAddress(toAddressDto(order.getBillingAddress()))
                .shippingAddress(toAddressDto(order.getShippingAddress()))
                .totalAmt(order.getTotalAmt())
                .paymentMode(order.getPayment().getPaymentMode())
                .subTotal(order.getSubTotal())
                .orderItems(toOrderItemsDto(order.getOrderItems()))
                .tax(order.getTax())
                .title(order.getTitle())
                .amount(order.getPayment().getAmount())
                .shippingCharges(order.getShippingCharges())
                .shippingMode(order.getShippingMode())
                .build();
    }

    private List<OrderItemDto> toOrderItemsDto(List<OrderItem> orderItems) {
        return orderItems.stream().map(orderItem -> {
            OrderItemDto orderItemDto = new OrderItemDto();
            orderItemDto.setQuantity(orderItem.getQuantity());
            orderItemDto.setProductId(orderItem.getProduct().getProductId());
            return orderItemDto;
        }).collect(Collectors.toList());
    }

    private AddressDto toAddressDto(Address address) {
        return AddressDto.builder()
                .address1(address.getAddress1())
                .address2(address.getAddress2())
                .city(address.getCity())
                .state(address.getState())
                .zip(address.getZip())
                .email(address.getEmail())
                .phone(address.getPhone())
                .build();
    }
}
