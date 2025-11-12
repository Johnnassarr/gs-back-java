package com.example.demo.infra.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.ai")
public class AiProperties {

    /**
     * System prompt sent to the language model describing its role.
     */
    private String systemPrompt =
            "Você é uma assistente especializada em tarefas e atitudes que ajudam o meio ambiente. "
                    + "Responda sempre em português brasileiro, com dicas práticas, encorajando ações sustentáveis.";

    /**
     * Keywords that should be present in a question to consider it within the domain.
     */
    private List<String> allowedKeywords = List.of(
            "reciclagem", "reciclar", "economia de agua", "economia de energia", "energia",
            "água", "plantio", "plantar", "árvore", "arvore", "sustentabilidade", "natureza",
            "meio ambiente", "ecologia", "compostagem", "lixo", "resíduos", "residuos",
            "plástico", "plastico", "carbono", "emissões", "emissoes", "reflorestamento");

    /**
     * Minimum number of matched keywords required to accept a question as valid.
     */
    private int minimumKeywordMatches = 1;

    /**
     * Message returned when a prompt is rejected for being off-topic.
     */
    private String outOfScopeMessage = "Desculpe — só posso ajudar com tarefas que ajudam a natureza.";

    public String getSystemPrompt() {
        return systemPrompt;
    }

    public void setSystemPrompt(String systemPrompt) {
        this.systemPrompt = systemPrompt;
    }

    public List<String> getAllowedKeywords() {
        return allowedKeywords;
    }

    public void setAllowedKeywords(List<String> allowedKeywords) {
        this.allowedKeywords = allowedKeywords;
    }

    public int getMinimumKeywordMatches() {
        return minimumKeywordMatches;
    }

    public void setMinimumKeywordMatches(int minimumKeywordMatches) {
        this.minimumKeywordMatches = minimumKeywordMatches;
    }

    public String getOutOfScopeMessage() {
        return outOfScopeMessage;
    }

    public void setOutOfScopeMessage(String outOfScopeMessage) {
        this.outOfScopeMessage = outOfScopeMessage;
    }
}

