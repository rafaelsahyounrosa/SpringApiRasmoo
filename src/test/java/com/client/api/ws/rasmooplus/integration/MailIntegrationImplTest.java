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
public class MailIntegrationImplTest {

    @Autowired
    private MailIntegration mailIntegration;


    @Test
    void sendMail(){
        mailIntegration.send("rafaelsahyoun@gmail.com", "Olá do RasmooPlus!", "Acesso Liberado!");
    }
}
