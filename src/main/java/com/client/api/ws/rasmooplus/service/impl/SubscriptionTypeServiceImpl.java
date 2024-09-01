package com.client.api.ws.rasmooplus.service.impl;

import com.client.api.ws.rasmooplus.controller.SubscriptionTypeController;
import com.client.api.ws.rasmooplus.dto.SubscriptionTypeDTO;
import com.client.api.ws.rasmooplus.exceptions.BadRequestException;
import com.client.api.ws.rasmooplus.exceptions.NotFoundException;
import com.client.api.ws.rasmooplus.mapper.SubscriptionTypeMapper;
import com.client.api.ws.rasmooplus.model.jpa.SubscriptionType;
import com.client.api.ws.rasmooplus.repository.jpa.SubscriptionTypeRepository;
import com.client.api.ws.rasmooplus.service.SubscriptionTypeService;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SubscriptionTypeServiceImpl implements SubscriptionTypeService {

    private static final String UPDATE = "update";
    private static final String DELETE = "delete";
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
       return getSubscriptionType(id).add(WebMvcLinkBuilder.linkTo(
               WebMvcLinkBuilder.methodOn(SubscriptionTypeController.class).findById(id))
                       .withSelfRel()
       )
               .add(WebMvcLinkBuilder.linkTo(
               WebMvcLinkBuilder.methodOn(SubscriptionTypeController.class).update(id, new SubscriptionTypeDTO()))
                       .withRel(UPDATE)
       )
               .add(WebMvcLinkBuilder.linkTo(
                       WebMvcLinkBuilder.methodOn(SubscriptionTypeController.class).delete(id))
                       .withRel(DELETE)
               );
    }



    @Override
    public SubscriptionType create(SubscriptionTypeDTO subscriptionTypeDTO) {

        if(Objects.nonNull(subscriptionTypeDTO.getId())) {
            throw new BadRequestException("Subscription type id already exists");
        }

        return repository.save(SubscriptionTypeMapper.fromDtoToEntity(subscriptionTypeDTO));
    }

    @Override
    public SubscriptionType update(Long id, SubscriptionTypeDTO subscriptionTypeDTO) {
        
       getSubscriptionType(id);
        subscriptionTypeDTO.setId(id);
        return repository.save(SubscriptionTypeMapper.fromDtoToEntity(subscriptionTypeDTO));
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
