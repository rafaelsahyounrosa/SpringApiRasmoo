package com.client.api.ws.rasmooplus.mapper.wsraspay;

import com.client.api.ws.rasmooplus.dto.UserPaymentInfoDTO;
import com.client.api.ws.rasmooplus.dto.wsraspay.CreditCardDTO;

public class CreditCardMapper {

    public static CreditCardDTO build(String documentNumber, UserPaymentInfoDTO dto){

        return CreditCardDTO.builder()
                .documentNumber(documentNumber)
                .cvv(Long.parseLong(dto.getCardSecurityCode()))
                .number(dto.getCardNumber())
                .month(dto.getCardExpirationMonth())
                .year(dto.getCardExpirationYear())
                .installments(dto.getInstallments())
                .build();
    }
}
