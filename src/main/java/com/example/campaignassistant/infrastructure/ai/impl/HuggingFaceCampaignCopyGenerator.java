package com.example.campaignassistant.infrastructure.ai.impl;

import com.example.campaignassistant.domain.model.Business;
import com.example.campaignassistant.domain.model.CampaignRule;
import com.example.campaignassistant.infrastructure.ai.CampaignCopyGenerator;
import com.example.campaignassistant.infrastructure.ai.GeneratedCampaignDraft;
import com.example.campaignassistant.infrastructure.ai.PromptBuilder;
import com.example.campaignassistant.infrastructure.ai.config.HuggingFaceProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Component
@Primary
public class HuggingFaceCampaignCopyGenerator implements CampaignCopyGenerator {

    private final PromptBuilder promptBuilder;
    private final HuggingFaceProperties properties;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public HuggingFaceCampaignCopyGenerator(PromptBuilder promptBuilder,
                                            HuggingFaceProperties properties,
                                            RestTemplate restTemplate,
                                            ObjectMapper objectMapper) {
        this.promptBuilder = promptBuilder;
        this.properties = properties;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    // @Override
    // public GeneratedCampaignDraft generateCopy(Business business, CampaignRule rule) {
    //     if (properties.getApiKey() == null || properties.getApiKey().isBlank()) {
    //         throw new IllegalStateException("Hugging Face API key is not configured (HUGGINGFACE_API_KEY).");
    //     }
    //     if (properties.getModelId() == null || properties.getModelId().isBlank()) {
    //         throw new IllegalStateException("Hugging Face model id is not configured (huggingface.model-id).");
    //     }

    //     String prompt = promptBuilder.buildPrompt(business, rule);

    //     String url = "https://router.huggingface.co/hf-inference/models/" + properties.getModelId();

    //     Map<String, Object> payload = new HashMap<>();
    //     payload.put("inputs", prompt);

    //     Map<String, Object> params = new HashMap<>();
    //     params.put("max_new_tokens", 300);
    //     params.put("temperature", 0.7);
    //     payload.put("parameters", params);

    //     HttpHeaders headers = new HttpHeaders();
    //     headers.setContentType(MediaType.APPLICATION_JSON);
    //     headers.setBearerAuth(properties.getApiKey());

    //     HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);

    //     ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
    //     if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
    //         throw new IllegalStateException("Hugging Face API error: " + response.getStatusCode());
    //     }

    //     String generatedText = extractGeneratedText(response.getBody());

    //     // Parse subject/body from the model's text
    //     return parseSubjectAndBody(generatedText, business, rule);
    // }

    // /**
    //  * Hugging Face text-generation typically returns:
    //  * [ { "generated_text": "..." } ]
    //  */
    // private String extractGeneratedText(String json) {
    //     try {
    //         List<Map<String, Object>> list = objectMapper.readValue(
    //                 json,
    //                 new TypeReference<List<Map<String, Object>>>() {}
    //         );
    //         if (list.isEmpty()) {
    //             throw new IllegalStateException("Empty response from Hugging Face.");
    //         }
    //         Object text = list.get(0).get("generated_text");
    //         if (text == null) {
    //             throw new IllegalStateException("No generated_text field in Hugging Face response.");
    //         }
    //         return text.toString();
    //     } catch (IOException e) {
    //         throw new IllegalStateException("Failed to parse Hugging Face response JSON.", e);
    //     }
    // }


    @Override
    public GeneratedCampaignDraft generateCopy(Business business, CampaignRule rule) {
        if (properties.getApiKey() == null || properties.getApiKey().isBlank()) {
            throw new IllegalStateException("Hugging Face API key is not configured (HUGGINGFACE_API_KEY).");
        }
        if (properties.getModelId() == null || properties.getModelId().isBlank()) {
            throw new IllegalStateException("Hugging Face model id is not configured (huggingface.model-id).");
        }

        String prompt = promptBuilder.buildPrompt(business, rule);

        // New router chat-completion endpoint
        String url = "https://router.huggingface.co/v1/chat/completions";

        Map<String, Object> payload = new HashMap<>();
        payload.put("model", properties.getModelId());  // e.g. "HuggingFaceTB/SmolLM3-3B:hf-inference"

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "user", "content", prompt));
        payload.put("messages", messages);

        // OpenAI-style params live at the top level, not inside "parameters"
        payload.put("max_tokens", 600);
        payload.put("temperature", 0.7);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(properties.getApiKey());  // "hf_..." token

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new IllegalStateException(
                    "Hugging Face API error: " + response.getStatusCode() + " body=" + response.getBody()
            );
        }

        String generatedText = extractFromChatCompletion(response.getBody());
        generatedText = stripThinking(generatedText);
        return parseSubjectAndBody(generatedText, business, rule);
    }

    private String stripThinking(String text) {
        if (text == null) {
            return null;
        }

        int start = text.indexOf("<think>");
        int end = text.indexOf("</think>");

        if (start != -1 && end != -1 && end > start) {
            // Text after </think> is the actual answer
            String after = text.substring(end + "</think>".length()).trim();
            if (!after.isEmpty()) {
                return after;
            }
        }

        // Fallback: if tags are weird/missing, just return original
        return text.trim();
    }

    private String extractFromChatCompletion(String json) {
        try {
            JsonNode root = objectMapper.readTree(json);
            JsonNode choices = root.path("choices");
            if (!choices.isArray() || choices.isEmpty()) {
                throw new IllegalStateException("No choices returned from Hugging Face.");
            }
            JsonNode message = choices.get(0).path("message");
            JsonNode contentNode = message.path("content");
            if (contentNode.isMissingNode() || contentNode.isNull()) {
                throw new IllegalStateException("No content in Hugging Face response.");
            }
            return contentNode.asText();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to parse Hugging Face chat completion JSON.", e);
        }
    }
    private GeneratedCampaignDraft parseSubjectAndBody(String generatedText,
                                                       Business business,
                                                       CampaignRule rule) {
        String subjectFallback = "Campaign for " + business.getName() + " - " + rule.getName();
        String bodyFallback = generatedText.trim();

        String subject = subjectFallback;
        String body = bodyFallback;

        String[] lines = generatedText.split("\\r?\\n");
        StringBuilder bodyBuilder = new StringBuilder();
        boolean inBody = false;

        for (String line : lines) {
            String trimmed = line.trim();
            if (trimmed.toLowerCase().startsWith("subject:")) {
                subject = trimmed.substring("subject:".length()).trim();
            } else if (trimmed.toLowerCase().startsWith("body:")) {
                inBody = true;
                // skip "Body:" line itself
            } else if (inBody) {
                bodyBuilder.append(line).append("\n");
            }
        }

        if (bodyBuilder.length() > 0) {
            body = bodyBuilder.toString().trim();
        }

        return new GeneratedCampaignDraft(subject, body);
    }
}
