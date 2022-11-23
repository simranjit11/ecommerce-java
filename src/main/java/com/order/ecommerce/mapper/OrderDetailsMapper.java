package com.order.ecommerce.mapper;

import com.order.ecommerce.dto.AddressDto;
import com.order.ecommerce.dto.OrderItemDto;
import com.order.ecommerce.enums.PaymentMode;
import com.order.ecommerce.enums.PaymentStatus;
import com.order.ecommerce.model.Address;
import com.order.ecommerce.model.OrderItem;
import com.order.ecommerce.model.OrderItemPk;
import com.order.ecommerce.model.Payment;
import com.order.ecommerce.repository.AddressRepository;
import com.order.ecommerce.repository.OrderItemRepository;
import com.order.ecommerce.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDetailsMapper {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment buildAndLoadPayment(Double amount, PaymentMode paymentMode) {
        Payment payment = new Payment(
                UUID.randomUUID().toString(),
                amount,
                paymentMode,
                UUID.randomUUID().toString(),
                PaymentStatus.PROCESSING,
                LocalDate.now(),
                null
        );

        paymentRepository.save(payment);
        return payment;
    }

    public Address buildAndLoadAddress(AddressDto addressDto) {
        Address addressEntity = toAddressEntity(addressDto);
        return addressRepository.save(addressEntity);
    }

    public List<OrderItem> buildOrderItems(List<OrderItemDto> orderItemsDtoList, String orderId) {
        List<OrderItem> orderItemList = orderItemsDtoList.stream()
                .map(orderItemDto ->
                        new OrderItem(new OrderItemPk(orderItemDto.getProductId(), orderId), null, null, orderItemDto.getQuantity()))
                .collect(Collectors.toList());
        orderItemRepository.saveAll(orderItemList);
        return orderItemList;
    }


    private Address toAddressEntity(AddressDto addressDto) {

        return Address.builder()
                .addressId(UUID.randomUUID().toString())
                .address1(addressDto.getAddress1())
                .address2(addressDto.getAddress2())
                .city(addressDto.getCity())
                .state(addressDto.getState())
                .zip(addressDto.getZip())
                .email(addressDto.getEmail())
                .phone(addressDto.getPhone())
                .createdAt(LocalDate.now())
                .order(null).build();
    }

}
