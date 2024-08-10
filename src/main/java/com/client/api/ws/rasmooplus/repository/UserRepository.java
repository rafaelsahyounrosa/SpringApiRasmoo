package com.client.api.ws.rasmooplus.repository;


import com.client.api.ws.rasmooplus.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
