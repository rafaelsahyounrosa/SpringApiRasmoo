package com.client.api.ws.rasmooplus.service;

import com.client.api.ws.rasmooplus.dto.LoginDTO;
import com.client.api.ws.rasmooplus.dto.TokenDTO;

public interface AuthenticationService {

    TokenDTO auth(LoginDTO dto);
}
