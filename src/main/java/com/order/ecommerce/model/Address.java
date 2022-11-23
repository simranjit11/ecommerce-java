package com.order.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "ecommerce_address")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @Column(name = "address_id", nullable = false, unique = true)
    private String addressId;

    @Column(name = "address1", nullable = false)
    private String address1;

    @Column(name = "address2", nullable = false)
    private String address2;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "zip", nullable = false)
    private String zip;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "createdAt", nullable = false)
    private LocalDate createdAt;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "billingAddress")
    private Order order;

}
