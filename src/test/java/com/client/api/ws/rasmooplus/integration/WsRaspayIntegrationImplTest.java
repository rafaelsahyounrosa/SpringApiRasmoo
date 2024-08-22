package com.client.api.ws.rasmooplus.integration;

import com.client.api.ws.rasmooplus.dto.wsraspay.CustomerDTO;
import com.client.api.ws.rasmooplus.dto.wsraspay.OrderDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class WsRaspayIntegrationImplTest {

    @Autowired
    private WsRaspayIntegration wsRaspayIntegration;


    @Test
    void createCustomerWhenDtoOk(){
        CustomerDTO customerDTO = new CustomerDTO(null, "94307116011", "rafaeltest@mail.com", "Rafael", "Rosa");
        wsRaspayIntegration.createCustomer(customerDTO);
    }

    @Test
    void createOrderWhenDtoOk(){
        OrderDTO orderDTO = new OrderDTO(null, "66c1e8fcc1851d46d4628c8e", BigDecimal.ZERO, "MONTH22");
        wsRaspayIntegration.createOrder(orderDTO);
    }
}
