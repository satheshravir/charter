package com.ecommerce.controller;

import com.ecommerce.model.CustomerOrder;
import com.ecommerce.model.OrderRepository;
import com.ecommerce.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class OrderController {
    private final OrderRepository orderRepository;
    private final OrderService orderService;

    OrderController(OrderRepository orderRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    @GetMapping("/customer/get_rewards/{id}")
    Map<String, Object> getCustomerRewards(@PathVariable Long id) {
        List<CustomerOrder> orderList = orderRepository.findByCustomerId(id);
        Double rewards = orderService.calculateRewards(orderList);
        return Map.of("customer_id", id, "rewards", rewards);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<String> handleAllExceptions(RuntimeException ex) {
        return new ResponseEntity<String>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
