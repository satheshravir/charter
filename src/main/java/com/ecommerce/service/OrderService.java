package com.ecommerce.service;

import com.ecommerce.model.CustomerOrder;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

/*
Service class that calculates custom business metrics
 */
@Service
public class OrderService {
    /*

     */
    public double calculateRewards(@NonNull final List<CustomerOrder> orderList) {
        return orderList.stream().map(x -> {
            double rewards = 0;
            if (x.getAmount() > 100) {
                rewards += (x.getAmount() - 100) * 2;
            }
            if (x.getAmount() > 50) {
                rewards += Math.min(50, x.getAmount() - 50);
            }
            return rewards;
        }).reduce(0D, (a, b) -> a + b);
    }
}
