package com.order.ecommerce.controller;

import com.order.ecommerce.dto.OrderCreateResponse;
import com.order.ecommerce.dto.OrderDto;
import com.order.ecommerce.enums.OrderStatus;
import com.order.ecommerce.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/v1"})
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping({"/orders"})
    @Operation(
            summary = "Create an order",
            description = "Create an order"
    )
    public ResponseEntity<OrderCreateResponse> createOrder(@RequestBody OrderDto orderDto) {
        return new ResponseEntity<>(orderService.createOrder(orderDto), HttpStatus.OK);
    }

    @GetMapping({"/orders/{orderId}"})
    public ResponseEntity<OrderDto> findOrderById(@PathVariable(name = "orderId") String orderId) {
        return new ResponseEntity<>(orderService.findOrderById(orderId), HttpStatus.OK);
    }

    @PatchMapping({"/orders/{orderId}"})
    public ResponseEntity<Void> updateOrderStatus(@PathVariable("orderId") String orderId, @RequestParam(name = "orderStatus") OrderStatus orderStatus) {
        orderService.updateOrderStatus(orderId, orderStatus);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
