package com.example.demo.service.ai;

import java.text.Normalizer;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.demo.infra.config.AiProperties;

@Component
public class EnvironmentalTopicFilter {

    private final Set<String> allowedKeywords;
    private final int minimumKeywordMatches;

    public EnvironmentalTopicFilter(AiProperties properties) {
        this.allowedKeywords = properties.getAllowedKeywords().stream()
                .map(EnvironmentalTopicFilter::normalize)
                .collect(Collectors.toSet());
        this.minimumKeywordMatches = Math.max(1, properties.getMinimumKeywordMatches());
    }

    public boolean isAllowed(String question) {
        if (question == null || question.isBlank()) {
            return false;
        }

        String normalizedQuestion = normalize(question);

        long matches = allowedKeywords.stream()
                .filter(normalizedQuestion::contains)
                .count();

        return matches >= minimumKeywordMatches;
    }

    private static String normalize(String value) {
        String lowerCase = value.toLowerCase(Locale.ROOT);
        String normalized = Normalizer.normalize(lowerCase, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{M}", "");
    }
}

