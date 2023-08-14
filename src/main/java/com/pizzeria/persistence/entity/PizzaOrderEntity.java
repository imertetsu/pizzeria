package com.pizzeria.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pizza_order")
@Getter
@Setter
@NoArgsConstructor
public class PizzaOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order", nullable = false)
    private Integer idOrder;
    @Column(name = "id_customer", nullable = false, length = 15)
    private String IdCustomer;
    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime date;
    @Column(nullable = false, columnDefinition = "DECIMAL(6,2)")
    private BigDecimal total;
    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private String method;
    @Column(name = "additional_notes", length = 200)
    private String additionalNotes;

    @OneToOne
    @JoinColumn(name = "id_customer", insertable = false, updatable = false)
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order")
    private List<OrderItemEntity> orderItemEntityList;
}
