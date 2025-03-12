package com.pks.example.repository;

import com.pks.example.entities.OrderEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity,Integer> {


    List<OrderEntity> findOrderEntitiesByItemNameOrderByOrderId(String itemName);

    @Query("select o from OrderEntity o where o.customerName = :customerName")
    List<OrderEntity> findCustomerNameUsingId(@Param("customerName") String customerName, Sort sort);
}
