package com.client.api.ws.rasmooplus.mapper;

import com.client.api.ws.rasmooplus.dto.UserPaymentInfoDTO;
import com.client.api.ws.rasmooplus.model.User;
import com.client.api.ws.rasmooplus.model.UserPaymentInfo;

public class UserPaymentInfoMapper {

    public static UserPaymentInfo fromDtoToEntity(UserPaymentInfoDTO dto, User user){

        return UserPaymentInfo.builder()
                .id(dto.getId())
                .cardNumber(dto.getCardNumber())
                .cardExpirationMonth(dto.getCardExpirationMonth())
                .cardExpirationYear(dto.getCardExpirationYear())
                .cardSecurityCode(dto.getCardSecurityCode())
                .price(dto.getPrice())
                .dtPayment(dto.getDtPayment())
                .user(user).build();
    }
}
