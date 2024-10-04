package com.client.api.ws.rasmooplus.service;

import com.client.api.ws.rasmooplus.dto.UserDTO;
import com.client.api.ws.rasmooplus.model.jpa.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {

    User create(UserDTO dto);

    User uploadPhoto(Long id, MultipartFile file) throws IOException;

    byte[] downloadPhoto(Long id);
}
