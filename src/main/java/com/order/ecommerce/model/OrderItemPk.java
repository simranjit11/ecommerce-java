package com.order.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemPk implements Serializable {

    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name = "order_id", nullable = false)
    private String orderId;

}
