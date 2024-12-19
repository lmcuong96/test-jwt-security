package com.example.demo.service;


import com.example.demo.dtos.RegisterDTO;

import java.util.UUID;

public interface AuthService {

    UUID createUser(RegisterDTO userDTO) ;

    boolean existsByUsername(String username);
}
