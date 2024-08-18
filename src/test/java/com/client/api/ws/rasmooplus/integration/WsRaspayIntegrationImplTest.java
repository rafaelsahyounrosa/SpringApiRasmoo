package com.client.api.ws.rasmooplus.integration;

import com.client.api.ws.rasmooplus.dto.wsraspay.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WsRaspayIntegrationImplTest {

    @Autowired
    private WsRaspayIntegration wsRaspayIntegration;


    @Test
    void createCustomerWhenDtoOk(){
        CustomerDTO customerDTO = new CustomerDTO(null, "94307116011", "rafaeltest@mail.com", "Rafael", "Rosa");

        wsRaspayIntegration.createCustomer(customerDTO);
    }
}
