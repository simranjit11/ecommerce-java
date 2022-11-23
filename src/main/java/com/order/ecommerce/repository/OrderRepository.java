package com.order.ecommerce.repository;

import com.order.ecommerce.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, String> {

}
