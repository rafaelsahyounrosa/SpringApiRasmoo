package com.client.api.ws.rasmooplus.mapper;

import com.client.api.ws.rasmooplus.dto.UserDTO;
import com.client.api.ws.rasmooplus.model.jpa.SubscriptionType;
import com.client.api.ws.rasmooplus.model.jpa.User;
import com.client.api.ws.rasmooplus.model.jpa.UserType;

public class UserMapper {

    public static User fromDtoToEntity(UserDTO userDTO, UserType userType, SubscriptionType subscriptionType) {

        return User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .cpf(userDTO.getCpf())
                .email(userDTO.getEmail())
                .phone(userDTO.getPhone())
                .dtSubscription(userDTO.getDtSubscription())
                .dtExpiration(userDTO.getDtExpiration())
                .userType(userType)
                .subscriptionType(subscriptionType)
                .build();
    }
}
