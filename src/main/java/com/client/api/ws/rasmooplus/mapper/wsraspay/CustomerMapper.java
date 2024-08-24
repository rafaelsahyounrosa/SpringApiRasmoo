package com.client.api.ws.rasmooplus.mapper.wsraspay;

import com.client.api.ws.rasmooplus.dto.wsraspay.CustomerDTO;
import com.client.api.ws.rasmooplus.model.User;

public class CustomerMapper {

    public static CustomerDTO build(User user){

        var fullName = user.getName().split(" ");
        var firstName = fullName[0];
        var lastName = fullName.length > 1 ? fullName[fullName.length - 1] : "";

        return CustomerDTO.builder()
                .email(user.getEmail())
                .cpf(user.getCpf())
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }
}
