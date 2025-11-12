package com.example.demo.service.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.example.demo.domain.model.dto.chat.ChatRequestDTO;
import com.example.demo.domain.model.dto.chat.ChatResponseDTO;
import com.example.demo.infra.config.AiProperties;

@Service
public class EnvironmentalChatService {

    private final ChatClient chatClient;
    private final EnvironmentalTopicFilter topicFilter;
    private final AiProperties properties;

    public EnvironmentalChatService(ChatClient.Builder chatClientBuilder,
                                    EnvironmentalTopicFilter topicFilter,
                                    AiProperties properties) {
        this.chatClient = chatClientBuilder.build();
        this.topicFilter = topicFilter;
        this.properties = properties;
    }

    public ChatResponseDTO chat(ChatRequestDTO request) {
        String question = request.question();

        if (!topicFilter.isAllowed(question)) {
            return new ChatResponseDTO(properties.getOutOfScopeMessage(), false);
        }

        String response = chatClient.prompt()
                .system(properties.getSystemPrompt())
                .user(question)
                .call()
                .content();

        return new ChatResponseDTO(response, true);
    }
}

