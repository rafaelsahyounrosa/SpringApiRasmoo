package com.client.api.ws.rasmooplus.dto.wsraspay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {


    private CreditCardDTO creditCard;
    private String customerId;
    private String orderId;
}
