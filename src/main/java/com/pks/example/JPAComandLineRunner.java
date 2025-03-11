package com.pks.example;

import com.pks.example.entities.OrderEntity;
import com.pks.example.repository.OrderJpaRepository;
import com.pks.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("jpa")
public class JPAComandLineRunner implements CommandLineRunner {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderJpaRepository orderJpaRepository;
    @Override
    public void run(String... args) throws Exception {
     //  orderRepository.getAllOrder();
      // orderRepository.createAndGetOrder();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(11);
        orderEntity.setItemName("Brush");
        orderEntity.setCustomerName("tubosun");
        orderEntity.setAddress("sagamu");
        System.out.println("Saving order from JPA");
        orderJpaRepository.save(orderEntity);
    }
}
