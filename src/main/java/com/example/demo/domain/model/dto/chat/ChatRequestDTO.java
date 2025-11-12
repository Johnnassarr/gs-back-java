package com.example.demo.domain.model.dto.chat;

import jakarta.validation.constraints.NotBlank;

public record ChatRequestDTO(
        @NotBlank(message = "A pergunta não pode estar vazia.")
        String question
) {
}

