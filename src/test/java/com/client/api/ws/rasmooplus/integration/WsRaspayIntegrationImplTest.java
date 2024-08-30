package com.client.api.ws.rasmooplus.integration;

import com.client.api.ws.rasmooplus.dto.wsraspay.CreditCardDTO;
import com.client.api.ws.rasmooplus.dto.wsraspay.CustomerDTO;
import com.client.api.ws.rasmooplus.dto.wsraspay.OrderDTO;
import com.client.api.ws.rasmooplus.dto.wsraspay.PaymentDTO;
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
        CustomerDTO customerDTO = new CustomerDTO(null, "24041035040", "rafaeltest2@mail.com", "Rafael2", "Rosa2");
        wsRaspayIntegration.createCustomer(customerDTO);
    }

    @Test
    void createOrderWhenDtoOk(){
        OrderDTO orderDTO = new OrderDTO(null, "66c72fbf542b14184517faf8", BigDecimal.ZERO, "MONTH22");
        wsRaspayIntegration.createOrder(orderDTO);
    }
    @Test
    void proccessPaymentDtoOk(){
        CreditCardDTO creditCardDTO = new CreditCardDTO(143L, "24041035040", 0L, 03L, "123412378234", 2035L);
        PaymentDTO paymentDTO = new PaymentDTO(creditCardDTO, "66c72fbf542b14184517faf8", "66c7301a542b14184517faf9");
        wsRaspayIntegration.processPayment(paymentDTO);
    }
}
