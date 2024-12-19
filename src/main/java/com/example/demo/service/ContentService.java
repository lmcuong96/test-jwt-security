package com.example.demo.service;

import com.example.demo.dtos.ContentDTO;

import java.util.List;

public interface ContentService {

    List<ContentDTO> findAll();

    boolean create(ContentDTO contentDTO);
}
