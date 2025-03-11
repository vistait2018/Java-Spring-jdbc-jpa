package com.pks.example.repository;

import com.pks.example.entities.OrderEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Transactional
public class OrderRepository {
    @PersistenceContext
    private EntityManager em;

    public void getAllOrder() {
       List<OrderEntity> orderEntityList =em.createQuery("SELECT o FROM OrderEntity o", OrderEntity.class)
                .getResultList();
        System.out.println("Result "+ orderEntityList);
    }


    public void createAndGetOrder() {

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(10);
        orderEntity.setItemName("Brush");
        orderEntity.setCustomerName("tubosun");
        orderEntity.setAddress("sagamu");
        em.persist(orderEntity);

        OrderEntity savedOrder =em.find(OrderEntity.class ,9);
        System.out.println("Order Saved "+ savedOrder);
    }
}
