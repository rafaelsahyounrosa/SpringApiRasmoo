package com.client.api.ws.rasmooplus.integration.impl;

import com.client.api.ws.rasmooplus.dto.wsraspay.CustomerDTO;
import com.client.api.ws.rasmooplus.dto.wsraspay.OrderDTO;
import com.client.api.ws.rasmooplus.dto.wsraspay.PaymentDTO;
import com.client.api.ws.rasmooplus.integration.WsRaspayIntegration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Component
public class WsRaspayIntegrationImpl implements WsRaspayIntegration {

    private RestTemplate restTemplate;
    private String BASE_URL = "https://raspay-api-61f5fa5fc34c.herokuapp.com";

    public WsRaspayIntegrationImpl() {
        restTemplate = new RestTemplate();
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {

        try {

            HttpHeaders headers = getHttpHeaders();

            HttpEntity<CustomerDTO> request = new HttpEntity<>(customerDTO, headers);
            ResponseEntity<CustomerDTO> response =
                    restTemplate.exchange(BASE_URL + "/ws-raspay/v1/customer", HttpMethod.POST, request, CustomerDTO.class);

            return response.getBody();

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public Boolean processPayment(PaymentDTO paymentDTO) {
        return null;
    }

    private static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String credential = "rasmooplus:r@sm00";
        String base64 = Base64.getEncoder().encodeToString(credential.getBytes());
        headers.add("Authorization", "Basic " + base64);
        return headers;
    }
}
