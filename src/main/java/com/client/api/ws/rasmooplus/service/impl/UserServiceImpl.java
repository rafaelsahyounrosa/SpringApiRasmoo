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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private static final String PNG = ".png";
    private static final String JPEG = ".jpeg";

    private final UserRepository userRepository;

    private final UserTypeRepository userTypeRepository;


    UserServiceImpl(UserRepository userRepository, UserTypeRepository userTypeRepository) {
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public User create(UserDTO dto) {

        if (Objects.nonNull(dto.getId())) {
            throw new BadRequestException("id must be null");
        }

        var userTypeOpt = userTypeRepository.findById(dto.getUserTypeId());

        if (userTypeOpt.isEmpty()) {
            throw new NotFoundException("userTypeId not found");
        }

        UserType userType = userTypeOpt.get();
        User user = UserMapper.fromDtoToEntity(dto, userType, null);
        return userRepository.save(user);
    }

    @Override
    public User uploadPhoto(Long id, MultipartFile file) throws IOException {
        String imgName = file.getOriginalFilename();
        String formatPNG =  imgName.substring(imgName.length() - 4);
        String formatJPEG =  imgName.substring(imgName.length() - 5);
        if (!(PNG.equalsIgnoreCase(formatPNG) || JPEG.equalsIgnoreCase(formatJPEG))) {
            throw new BadRequestException("Imagem deve possuir formato JPEG ou PNG.");
        }
        User user = findById(id);
        user.setPhotoName(file.getOriginalFilename());
        user.setPhoto(file.getBytes());
        return userRepository.save(user);
    }

    @Override
    public byte[] downloadPhoto(Long id) {
        User user = findById(id);
        if (Objects.isNull(user.getPhoto())) {
            throw new BadRequestException("Usuário não possui foto");
        }
        return user.getPhoto();
    }

    private User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }
}
