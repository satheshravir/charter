package com.ecommerce.controller;

import com.ecommerce.model.CustomerOrder;
import com.ecommerce.model.OrderRepository;
import com.ecommerce.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@ExtendWith(MockitoExtension.class)
public class CustomerOrderControllerTest {
    private MockMvc mvc;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(orderController)
                .build();
    }

    @Test
    public void test_validResponse() throws Exception {
        CustomerOrder o1 = new CustomerOrder();
        Mockito.lenient().when(orderRepository.findByCustomerId(1L))
                .thenReturn(Arrays.asList(o1));
        Mockito.lenient().when(orderService.calculateRewards(Arrays.asList(o1)))
                .thenReturn(90D);
        MockHttpServletResponse response = mvc.perform(
                        get("/customer/get_rewards/1")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        System.out.println(response.getContentAsString());
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    public void test_invalidHttpMethod() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                        post("/customer/get_rewards/1")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        Assertions.assertEquals(405, response.getStatus());
    }

    @Test
    public void test_invalidInput() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                        get("/customer/get_rewards/av")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        System.out.println(response.getStatus());
        Assertions.assertEquals(500, response.getStatus());
    }
}
