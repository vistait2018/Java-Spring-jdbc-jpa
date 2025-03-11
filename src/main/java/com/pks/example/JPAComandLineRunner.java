package com.pks.example;

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
    @Override
    public void run(String... args) throws Exception {
       orderRepository.getAllOrder();
       orderRepository.createAndGetOrder();
    }
}
