package com.client.api.ws.rasmooplus.service;

import com.client.api.ws.rasmooplus.dto.SubscriptionTypeDTO;
import com.client.api.ws.rasmooplus.model.SubscriptionType;

import java.util.List;

public interface SubscriptionTypeService {

    List<SubscriptionType> findAll();
    SubscriptionType findById(Long id);
    SubscriptionType create(SubscriptionTypeDTO subscriptionTypeDTO);
    SubscriptionType update(Long id, SubscriptionTypeDTO subscriptionTypeDTO);
    void delete(Long id);
}
