package com.pks.example;

import com.pks.example.entities.OrderEntity;
import com.pks.example.repository.OrderJpaRepository;
import com.pks.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

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

      orderJpaRepository.deleteById(9);

      List<OrderEntity> orderEntityList = new ArrayList<>();
        IntStream.range(20,100)
                .forEach(e->{
                    OrderEntity o =new OrderEntity();
                    o.setOrderId(e);
                    o.setItemName("item "+e);
                   o.setCustomerName("name "+e);
                   o.setAddress("address "+e);
                   orderEntityList.add(o);
                });

        orderJpaRepository.saveAll(orderEntityList);

        System.out.println("save orders " +orderEntityList);
        List<OrderEntity> allOrders = orderJpaRepository.findAll();
        System.out.println("ALl orders "+allOrders);

        List<OrderEntity> orderEntities = orderJpaRepository
                .findOrderEntitiesByItemNameOrderByOrderId("item 71");
        System.out.println(orderEntities);


        List<OrderEntity> sortedOrderEntities = orderJpaRepository
                .findCustomerNameUsingId("name 66",
                        Sort.by("itemName"));
        System.out.println(sortedOrderEntities);
    }
}
