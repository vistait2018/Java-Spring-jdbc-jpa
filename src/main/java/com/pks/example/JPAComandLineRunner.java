package com.pks.example;

import com.pks.example.entities.OrderEntity;
import com.pks.example.repository.OrderJpaRepository;
import com.pks.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("jpa")
public class JPAComandLineRunner implements CommandLineRunner {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderJpaRepository orderJpaRepository;
    @Override
    public void run(String... args) throws Exception {


        System.out.println("Deleting order");
        orderJpaRepository.deleteAll();
     //  orderRepository.getAllOrder();
      // orderRepository.createAndGetOrder();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(9);
        orderEntity.setItemName("Brush");
        orderEntity.setCustomerName("tubosun");
        orderEntity.setAddress("sagamu");
        System.out.println("Saving order from JPA");
        orderJpaRepository.save(orderEntity);

        Optional<OrderEntity> orderById = orderJpaRepository.findById(9);
        System.out.println(orderById.get());

      orderById.get().setAddress("Lagos");
      orderJpaRepository.save(orderById.get());
    }
}
