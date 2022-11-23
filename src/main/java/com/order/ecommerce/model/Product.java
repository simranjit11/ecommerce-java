package com.order.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ecommerce_product")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Persistable<String> {
    @Id
    @Column(name = "product_id", nullable = false, unique = true)
    private String productId;

    @Transient
    private boolean update;

    @Column(name = "sku", nullable = false)
    private String sku;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "createdAt", nullable = false)
    private LocalDate createdAt;

    @OneToMany(targetEntity = OrderItem.class, fetch = FetchType.LAZY, mappedBy = "product")
    private List<OrderItem> orderItems;

    @Override
    public String getId() {
        return this.productId;
    }

    @Override
    public boolean isNew() {
        return !this.update;
    }

    @PrePersist
    @PostLoad
    void markUpdated() {
        this.update = true;
    }
}
