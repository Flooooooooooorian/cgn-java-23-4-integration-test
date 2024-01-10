package de.neuefische.cgnjava234webclient.openai.service;

import de.neuefische.cgnjava234webclient.openai.models.ChatGptMessage;
import de.neuefische.cgnjava234webclient.openai.models.ChatGptRequest;
import de.neuefische.cgnjava234webclient.openai.models.ChatGptResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ChatGPTService {

    private final RestClient restClient;

    public ChatGPTService(@Value("${app.chatgpt.api.url}") String url,
                          @Value("${app.chatgpt.api.key}") String apiKey,
                          @Value("${app.chatgpt.api.org}") String org) {

        restClient = RestClient.builder()
                .baseUrl(url)
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .defaultHeader("OpenAI-Organization", org)
                .build();
    }


    public String chatGpt(String message) {

        ChatGptRequest requestBody = new ChatGptRequest(List.of(new ChatGptMessage("user", message)), "gpt-3.5-turbo");

        ChatGptResponse response = restClient.post()
                .uri("/completions")
                .body(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(ChatGptResponse.class);

        return response.choices().get(0).message().content();
    }
}
