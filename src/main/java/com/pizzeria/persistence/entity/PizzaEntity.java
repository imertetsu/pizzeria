package com.pizzeria.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pizza")
@EntityListeners(AuditingEntityListener.class)//para que sea auditable este Entity
@Getter
@Setter
@NoArgsConstructor
public class PizzaEntity extends AuditableEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza", nullable = false) //nunca sera nulo
    private Integer idPizza;

    @Column(nullable = false, length = 30, unique = true)
    private String name;
    @Column(nullable = false, length = 150)
    private String description;
    @Column(nullable = false, columnDefinition = "Decimal(5,2)")
    private BigDecimal price;
    @Column(columnDefinition = "TINYINT")
    private Boolean vegetarian;
    @Column(columnDefinition = "TINYINT")
    private Boolean vegan;
    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean available;
}
