package com.order.ecommerce.model;

import com.order.ecommerce.enums.PaymentMode;
import com.order.ecommerce.enums.PaymentStatus;
import lombok.AllArgsConstructor;
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
@Data
@Table(name = "ecommerce_payment")
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @Column(name = "payment_id", nullable = false, unique = true)
    private String paymentId;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "payment_mode", nullable = false)
    private PaymentMode paymentMode;

    @Column(name = "confirmation_number", nullable = false)
    private String confirmationNumber;

    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;

    @Column(name = "createdAt", nullable = false)
    private LocalDate createdAt;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "payment")
    private Order order;

}
