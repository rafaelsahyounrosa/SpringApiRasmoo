package com.client.api.ws.rasmooplus.service.impl;

import com.client.api.ws.rasmooplus.dto.UserDTO;
import com.client.api.ws.rasmooplus.exceptions.BadRequestException;
import com.client.api.ws.rasmooplus.exceptions.NotFoundException;
import com.client.api.ws.rasmooplus.mapper.UserMapper;
import com.client.api.ws.rasmooplus.model.jpa.User;
import com.client.api.ws.rasmooplus.model.jpa.UserType;
import com.client.api.ws.rasmooplus.repository.jpa.UserRepository;
import com.client.api.ws.rasmooplus.repository.jpa.UserTypeRepository;
import com.client.api.ws.rasmooplus.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository;
   private final UserTypeRepository userTypeRepository;

   public UserServiceImpl(UserRepository userRepository, UserTypeRepository userTypeRepository) {
       this.userRepository = userRepository;
       this.userTypeRepository = userTypeRepository;
   }

    @Override
    public User create(UserDTO userDTO) {

       if(Objects.nonNull(userDTO.getId())){
           throw new BadRequestException("Username already exists");
       }

       var userTypeOptional = userTypeRepository.findById(userDTO.getUserTypeId());

       if(userTypeOptional.isEmpty()){
           throw new NotFoundException("User type not found");
       }

        UserType userType = userTypeOptional.get();
        User user = UserMapper.fromDtoToEntity(userDTO, userType, null);

       return userRepository.save(user);
    }
}
