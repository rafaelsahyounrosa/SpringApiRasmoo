package com.client.api.ws.rasmooplus.mapper.wsraspay;

import com.client.api.ws.rasmooplus.dto.PaymentProccessDTO;
import com.client.api.ws.rasmooplus.dto.wsraspay.OrderDTO;

public class OrderMapper {

    public static OrderDTO build(String customerId, PaymentProccessDTO paymentProccessDTO) {

        return OrderDTO.builder()
                .customerId(customerId)
                .productAcronym(paymentProccessDTO.getProductKey())
                .discount(paymentProccessDTO.getDiscount())
                .build();
    }
}
