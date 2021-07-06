package com.orders.repo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<OrderPersist, Long> {
    OrderPersist getOrderById(Long id);

}
