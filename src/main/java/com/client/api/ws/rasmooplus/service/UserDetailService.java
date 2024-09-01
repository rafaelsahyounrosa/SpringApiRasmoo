package com.client.api.ws.rasmooplus.service;

import com.client.api.ws.rasmooplus.dto.UserDetailsDTO;
import com.client.api.ws.rasmooplus.model.jpa.UserCredentials;

public interface UserDetailService {

    UserCredentials loadUserByUsernameAndPass(String username, String pass);

    void sendRecoveryCode(String email);

    boolean recoveryCodeIsValid(String recoveryCode, String email);

    void updatePasswordByRecoveryCode(UserDetailsDTO userDetailsDto);
}
