package com.example.campaignassistant.infrastructure.ai.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "huggingface")
public class HuggingFaceProperties {

    /**
     * Hugging Face API key (token), usually set via env var HUGGINGFACE_API_KEY.
     */
    private String apiKey;

    /**
     * Model id, e.g. HuggingFaceH4/zephyr-7b-beta.
     */
    private String modelId;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }
}
