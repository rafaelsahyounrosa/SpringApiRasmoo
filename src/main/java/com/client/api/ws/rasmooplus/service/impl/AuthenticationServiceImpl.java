package com.client.api.ws.rasmooplus.service.impl;

import com.client.api.ws.rasmooplus.dto.LoginDTO;
import com.client.api.ws.rasmooplus.dto.TokenDTO;
import com.client.api.ws.rasmooplus.exceptions.BadRequestException;
import com.client.api.ws.rasmooplus.model.jpa.UserCredentials;
import com.client.api.ws.rasmooplus.service.AuthenticationService;
import com.client.api.ws.rasmooplus.service.TokenService;
import com.client.api.ws.rasmooplus.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserDetailService userDetailsService;

    @Autowired
    private TokenService tokenService;

    @Override
    public TokenDTO auth(LoginDTO dto) {
        try {
            UserCredentials userCredentials = userDetailsService.loadUserByUsernameAndPass(dto.getUsername(), dto.getPassword());
            String token = tokenService.getToken(userCredentials.getId());
            return TokenDTO.builder().token(token).type("Bearer").build();
        } catch (Exception e) {
            throw new BadRequestException("Erro while trying to format the token - "+e.getMessage());
        }
    }
}
