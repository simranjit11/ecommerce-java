package com.order.ecommerce.repository;

import com.order.ecommerce.model.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, String> {
}
