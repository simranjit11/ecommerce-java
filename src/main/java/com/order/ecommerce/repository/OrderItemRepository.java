package com.order.ecommerce.repository;

import com.order.ecommerce.model.OrderItem;
import com.order.ecommerce.model.OrderItemPk;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItem, OrderItemPk> {
}
