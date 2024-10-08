package com.client.api.ws.rasmooplus.mapper;

import com.client.api.ws.rasmooplus.dto.SubscriptionTypeDTO;
import com.client.api.ws.rasmooplus.model.jpa.SubscriptionType;

public class SubscriptionTypeMapper {

    public static SubscriptionType fromDtoToEntity(final SubscriptionTypeDTO subscriptionTypeDTO) {

        return SubscriptionType.builder()
                .id(subscriptionTypeDTO.getId())
                .name(subscriptionTypeDTO.getName())
                .accessMonths(subscriptionTypeDTO.getAccessMonths())
                .price(subscriptionTypeDTO.getPrice())
                .productKey(subscriptionTypeDTO.getProductKey())
                .build();
    }
}
