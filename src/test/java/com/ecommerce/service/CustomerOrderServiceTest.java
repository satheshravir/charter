package com.ecommerce.service;

import com.ecommerce.model.CustomerOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class CustomerOrderServiceTest {
    @Test
    public void test_handleEmptyOrderList() {
        List<CustomerOrder> emptyList = Collections.EMPTY_LIST;
        OrderService service = new OrderService();
        Assertions.assertTrue(0 == service.calculateRewards(emptyList));
    }

    @Test
    public void test_handleNullOrderList() {
        List<CustomerOrder> nullList = null;
        OrderService service = new OrderService();
        Assertions.assertThrows(NullPointerException.class,
                () -> service.calculateRewards(nullList),
                "Null pointer exception expected");
    }

    @Test
    public void test_handleValidOrderList() {
        CustomerOrder o1 = new CustomerOrder();
        o1.setAmount(120D);
        o1.setCustomerId(1L);
        o1.setId(1L);

        CustomerOrder o2 = new CustomerOrder();
        o2.setAmount(80D);
        o2.setCustomerId(1L);
        o2.setId(2L);

        List<CustomerOrder> orderList = Arrays.asList(o1, o2);
        OrderService service = new OrderService();
        Assertions.assertTrue(120L == service.calculateRewards(orderList));
    }

    @Test
    public void test_handleValidSingleOrderList() {
        CustomerOrder o1 = new CustomerOrder();
        o1.setAmount(120D);
        o1.setCustomerId(1L);
        o1.setId(1L);

        List<CustomerOrder> orderList = Arrays.asList(o1);
        OrderService service = new OrderService();
        Assertions.assertTrue(90L == service.calculateRewards(orderList));
    }

}
