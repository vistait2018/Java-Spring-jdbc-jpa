package com.pks.example.repository;

import com.pks.example.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity,Integer> {
}
