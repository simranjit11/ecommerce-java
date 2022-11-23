package com.order.ecommerce.controller;

import com.order.ecommerce.dto.OrderCreateResponse;
import com.order.ecommerce.dto.OrderDto;
import com.order.ecommerce.enums.OrderStatus;
import com.order.ecommerce.service.OrderService;
import com.order.ecommerce.util.OrderUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith({MockitoExtension.class})
public final class OrderControllerUnitTest {
    private final OrderDto orderDtoRequest;
    private final OrderCreateResponse mockOrderCreateResponse;
    private final OrderDto mockOrderGetResponse;
    @Mock
    public OrderService orderService;
    @InjectMocks
    public OrderController orderController;

    public OrderControllerUnitTest() {
        this.orderDtoRequest = OrderUtil.createTestOrder();
        this.mockOrderCreateResponse = new OrderCreateResponse("2e99fe21-2243-4004-9640-e992bbcc5040", OrderStatus.PROCESSING);
        this.mockOrderGetResponse = OrderUtil.createMockOrderResponse();
    }

    @Test
    public void testCreateOrder() {
        Mockito.when(orderService.createOrder(this.orderDtoRequest)).thenReturn(this.mockOrderCreateResponse);
        ResponseEntity actualResponse = orderController.createOrder(this.orderDtoRequest);
        Assertions.assertThat(actualResponse).isEqualTo(new ResponseEntity<>(mockOrderCreateResponse, HttpStatus.OK));
    }

    @Test
    public void testGetOrder() {
        Mockito.when(orderService.findOrderById("2e99fe21-2243-4004-9640-e992bbcc5040")).thenReturn(this.mockOrderGetResponse);
        ResponseEntity actualResponse = orderController.findOrderById("2e99fe21-2243-4004-9640-e992bbcc5040");
        Assertions.assertThat(actualResponse).isEqualTo(new ResponseEntity<>(mockOrderGetResponse, HttpStatus.OK));
    }
}
