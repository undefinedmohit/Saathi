package com.saathi;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.Map;

@Service
public class OllamaService {

    private final RestClient restClient = RestClient.create("http://localhost:11434");

    public void getSuggestion(String code) {
        try {
            String prompt = """
                    You are a helpful coding companion named Saathi.
                    Review this Java code briefly. Give ONE short, useful suggestion
                    or point out ONE issue if any. Keep it under 2 sentences,
                    simple spoken English.
                    Code:
                    %s
                    """.formatted(code);

            Map<String, Object> requestBody = Map.of(
                    "model", "codellama:7b",
                    "prompt", prompt,
                    "stream", false);

            Map response = restClient.post()
                    .uri("/api/generate")
                    .body(requestBody)
                    .retrieve()
                    .body(Map.class);
            String suggestion = (String) response.get("response");
            System.out.println("🤖 Saathi says: " + suggestion);

        } catch (Exception e) {
            System.out.println("⚠️ Saathi couldn't analyze: " + e.getMessage());
        }
    }
}