package com.client.api.ws.rasmooplus.controller;


import com.client.api.ws.rasmooplus.dto.UserTypeDTO;
import com.client.api.ws.rasmooplus.model.jpa.UserType;
import com.client.api.ws.rasmooplus.service.UserTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-type")
public class UserTypeController {

    @Autowired
    private UserTypeService userTypeService;

    @GetMapping
    @Cacheable(value = "userType")
    public ResponseEntity<List<UserType>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userTypeService.findAll());
    }

    @PostMapping
    public ResponseEntity<UserType> create(@Valid @RequestBody UserTypeDTO userTypeDTO) {


        return ResponseEntity.status(HttpStatus.CREATED).body(userTypeService.create(userTypeDTO));
    }

}
