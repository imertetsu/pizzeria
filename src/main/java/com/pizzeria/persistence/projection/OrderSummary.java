package com.pizzeria.persistence.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface OrderSummary {
    Integer getIdOrder();
    String getCustomerName();
    LocalDateTime getOrderDate();
    BigDecimal getOrderTotal();
    String getPizzaNames();

    //los nombres en las consultas que definimos en la interfaz OrderSummary, por ejemplo el getIdOrder, debe ir con "po.id_order AS idOrder"
}
