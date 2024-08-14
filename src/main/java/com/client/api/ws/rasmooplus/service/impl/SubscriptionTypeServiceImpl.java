package com.client.api.ws.rasmooplus.service.impl;

import com.client.api.ws.rasmooplus.dto.SubscriptionTypeDTO;
import com.client.api.ws.rasmooplus.exceptions.BadRequestException;
import com.client.api.ws.rasmooplus.exceptions.NotFoundException;
import com.client.api.ws.rasmooplus.model.SubscriptionType;
import com.client.api.ws.rasmooplus.repository.SubscriptionTypeRepository;
import com.client.api.ws.rasmooplus.service.SubscriptionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
       return getSubscriptionType(id);
    }



    @Override
    public SubscriptionType create(SubscriptionTypeDTO subscriptionTypeDTO) {

        if(Objects.nonNull(subscriptionTypeDTO.getId())) {
            throw new BadRequestException("Subscription type id already exists");
        }

        return repository.save(SubscriptionType.builder()
                                                .id(subscriptionTypeDTO.getId())
                                                .name(subscriptionTypeDTO.getName())
                                                .accessMonth(subscriptionTypeDTO.getAccessMonth())
                                                .price(subscriptionTypeDTO.getPrice())
                                                .productKey(subscriptionTypeDTO.getProductKey())
                                                .build());
    }

    @Override
    public SubscriptionType update(Long id, SubscriptionTypeDTO subscriptionTypeDTO) {
        
       getSubscriptionType(id);

        return repository.save(SubscriptionType.builder()
                .id(id)
                .name(subscriptionTypeDTO.getName())
                .accessMonth(subscriptionTypeDTO.getAccessMonth())
                .price(subscriptionTypeDTO.getPrice())
                .productKey(subscriptionTypeDTO.getProductKey())
                .build());
    }

    @Override
    public void delete(Long id) {
        getSubscriptionType(id);
        repository.deleteById(id);
    }

    private SubscriptionType getSubscriptionType(Long id) {
        Optional<SubscriptionType> optional = repository.findById(id);

        if (optional.isEmpty()) {
            throw new NotFoundException("Subscription type not found");
        }
        return optional.get();
    }
}
