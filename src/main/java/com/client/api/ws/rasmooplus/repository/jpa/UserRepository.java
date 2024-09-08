package com.client.api.ws.rasmooplus.repository.jpa;


import com.client.api.ws.rasmooplus.model.jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
