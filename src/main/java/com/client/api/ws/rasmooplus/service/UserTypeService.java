package com.client.api.ws.rasmooplus.service;

import com.client.api.ws.rasmooplus.dto.UserTypeDTO;
import com.client.api.ws.rasmooplus.model.jpa.UserType;

import java.util.List;

public interface UserTypeService {

    UserType create(UserTypeDTO userTypeDTO);
    List<UserType> findAll();
}
