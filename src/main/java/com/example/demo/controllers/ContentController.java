package com.example.demo.controllers;

import com.example.demo.dtos.ContentDTO;
import com.example.demo.service.ContentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/api/content")
public class ContentController {

    private final ContentService contentService;

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        var contentList = contentService.findAll();
        if (contentList.isEmpty()) {
            ResponseEntity.noContent();
        }

        return ResponseEntity.ok(contentList);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody ContentDTO contentDTO) {
        if (contentDTO == null) {
            return ResponseEntity.badRequest().body("Content is required");
        }
        contentService.create(contentDTO);
        return ResponseEntity.ok("Content created");
    }

}
