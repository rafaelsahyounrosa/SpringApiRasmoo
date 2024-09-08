package com.client.api.ws.rasmooplus.controller;

import com.client.api.ws.rasmooplus.dto.UserDTO;
import com.client.api.ws.rasmooplus.model.jpa.User;
import com.client.api.ws.rasmooplus.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userDTO));
    }

//    @PatchMapping(value = "/{id}/upload-photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<User> uploadPhoto(@PathVariable("id") Long id, @RequestPart("file") MultipartFile file) throws IOException {
//        return ResponseEntity.status(HttpStatus.OK).body(userService.uploadPhoto(id, file));
//    }
//
//
//    @GetMapping(value = "/{id}/photo", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
//    public ResponseEntity<byte[]> downloadPhoto(@PathVariable("id") Long id) {
//        return ResponseEntity.status(HttpStatus.OK).body(userService.downloadPhoto(id));
//    }
}
