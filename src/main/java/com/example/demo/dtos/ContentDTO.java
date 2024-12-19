package com.example.demo.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContentDTO {
    private UUID id;

    @NotNull(message = "Content is required")
    private String content;
}
