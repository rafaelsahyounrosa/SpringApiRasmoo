package com.client.api.ws.rasmooplus.service.impl;

import com.client.api.ws.rasmooplus.dto.UserTypeDTO;
import com.client.api.ws.rasmooplus.exceptions.BadRequestException;
import com.client.api.ws.rasmooplus.mapper.UserTypeMapper;
import com.client.api.ws.rasmooplus.model.UserType;
import com.client.api.ws.rasmooplus.repository.UserTypeRepository;
import com.client.api.ws.rasmooplus.service.UserTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserTypeServiceImpl implements UserTypeService {

   private final UserTypeRepository repository;

   public UserTypeServiceImpl(UserTypeRepository userTypeRepository) {
       this.repository = userTypeRepository;
   }

    @Override
    public UserType create(UserTypeDTO userTypeDTO) {

       if(Objects.nonNull(userTypeDTO.getId())){
           throw new BadRequestException("User Type already exists");
       }

       return repository.save(UserTypeMapper.fromDtoToEntity(userTypeDTO));
    }

    @Override
    public List<UserType> findAll() {
        return repository.findAll();
    }
}
