package com.client.api.ws.rasmooplus.repository.redis;


import com.client.api.ws.rasmooplus.model.redis.UserRecoveryCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRecoveryCodeRepository extends CrudRepository<UserRecoveryCode, String> {

    Optional<UserRecoveryCode> findByEmail(String email);
}