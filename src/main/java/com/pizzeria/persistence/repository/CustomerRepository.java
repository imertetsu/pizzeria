package com.pizzeria.persistence.repository;

import com.pizzeria.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends ListCrudRepository<CustomerEntity, String> {
    @Query(value = "Select c from CustomerEntity c where c.phoneNumber = :phone") //estructura JPQL
    CustomerEntity findByPhone(@Param("phone") String phone);
}
