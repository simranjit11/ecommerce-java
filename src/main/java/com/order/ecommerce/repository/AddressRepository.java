package com.order.ecommerce.repository;

import com.order.ecommerce.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, String> {
}
