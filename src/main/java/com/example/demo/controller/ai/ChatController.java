package com.example.demo.controller.ai;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.model.dto.chat.ChatRequestDTO;
import com.example.demo.domain.model.dto.chat.ChatResponseDTO;
import com.example.demo.service.ai.EnvironmentalChatService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/chat")
@Validated
public class ChatController {

    private final EnvironmentalChatService chatService;

    public ChatController(EnvironmentalChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ResponseEntity<ChatResponseDTO> chat(@Valid @RequestBody ChatRequestDTO request) {
        ChatResponseDTO response = chatService.chat(request);
        return ResponseEntity.ok(response);
    }
}

