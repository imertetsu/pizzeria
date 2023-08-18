package com.pizzeria.service.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data //para crear getters setters constructors etc
public class UpdatePizzaPriceDTO {
    private int idPizza;
    private BigDecimal newPrice;
}
