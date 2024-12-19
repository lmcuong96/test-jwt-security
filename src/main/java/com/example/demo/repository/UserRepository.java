package com.example.demo.repository;


import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> , JpaSpecificationExecutor<User> {

    User findByUsername(String username);
    boolean existsByUsername(String username);
}
