package com.client.api.ws.rasmooplus.service;

import com.client.api.ws.rasmooplus.dto.UserDTO;
import com.client.api.ws.rasmooplus.model.jpa.User;

public interface UserService {

    User create(UserDTO userDTO);
}
