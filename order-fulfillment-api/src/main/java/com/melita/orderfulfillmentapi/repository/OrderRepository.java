package com.melita.orderfulfillmentapi.repository;

import com.melita.orderfulfillmentapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
