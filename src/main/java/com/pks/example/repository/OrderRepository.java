package com.pks.example.repository;

import com.pks.example.entities.OrderEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderRepository {
    @PersistenceContext
    private EntityManager em;

    public void getAllOrder() {
       List<OrderEntity> orderEntityList =em.createQuery("SELECT o FROM OrderEntity o", OrderEntity.class)
                .getResultList();
        System.out.println("Result "+ orderEntityList);
    }
}
