package com.client.api.ws.rasmooplus.integration;

import com.client.api.ws.rasmooplus.dto.wsraspay.CustomerDTO;
import com.client.api.ws.rasmooplus.dto.wsraspay.OrderDTO;
import com.client.api.ws.rasmooplus.dto.wsraspay.PaymentDTO;

public interface WsRaspayIntegration {

    CustomerDTO createCustomer(CustomerDTO customerDTO);
    OrderDTO createOrder(OrderDTO orderDTO);
    Boolean processPayment(PaymentDTO paymentDTO);
}
