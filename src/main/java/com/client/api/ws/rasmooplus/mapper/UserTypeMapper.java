package com.client.api.ws.rasmooplus.mapper;

import com.client.api.ws.rasmooplus.dto.UserDTO;
import com.client.api.ws.rasmooplus.dto.UserTypeDTO;
import com.client.api.ws.rasmooplus.model.SubscriptionType;
import com.client.api.ws.rasmooplus.model.User;
import com.client.api.ws.rasmooplus.model.UserType;

public class UserTypeMapper {

    public static UserType fromDtoToEntity(final UserTypeDTO userTypeDTO) {

        return UserType.builder()
                .id(userTypeDTO.getId())
                .name(userTypeDTO.getName())
                .description(userTypeDTO.getDescription())
                .build();
    }
}
