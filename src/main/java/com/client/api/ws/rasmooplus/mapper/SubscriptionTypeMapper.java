package com.client.api.ws.rasmooplus.mapper;

import com.client.api.ws.rasmooplus.dto.SubscriptionTypeDTO;
import com.client.api.ws.rasmooplus.model.SubscriptionType;

public class SubscriptionTypeMapper {

    public static SubscriptionType fromDtoToEntity(final SubscriptionTypeDTO subscriptionTypeDTO) {

        return SubscriptionType.builder()
                .id(subscriptionTypeDTO.getId())
                .name(subscriptionTypeDTO.getName())
                .accessMonth(subscriptionTypeDTO.getAccessMonth())
                .price(subscriptionTypeDTO.getPrice())
                .productKey(subscriptionTypeDTO.getProductKey())
                .build();
    }
}
