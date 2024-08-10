package com.client.api.ws.rasmooplus.service.impl;

import com.client.api.ws.rasmooplus.exceptions.NotFoundException;
import com.client.api.ws.rasmooplus.model.SubscriptionType;
import com.client.api.ws.rasmooplus.repository.SubscriptionTypeRepository;
import com.client.api.ws.rasmooplus.service.SubscriptionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionTypeServiceImpl implements SubscriptionTypeService {

    private final SubscriptionTypeRepository repository;


    //Using dependency injection through the constructor
    public SubscriptionTypeServiceImpl(SubscriptionTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SubscriptionType> findAll() {
        return repository.findAll();
    }

    @Override
    public SubscriptionType findById(Long id) {
        Optional<SubscriptionType> optional = repository.findById(id);

        if (optional.isEmpty()) {
            throw new NotFoundException("Subscription type not found");
        }

        return optional.get();
    }

    @Override
    public SubscriptionType create(SubscriptionType subscriptionType) {
        return null;
    }

    @Override
    public SubscriptionType update(Long id, SubscriptionType subscriptionType) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
