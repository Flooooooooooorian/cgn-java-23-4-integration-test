package de.neuefische.cgnjava234webclient.openai.models;

import java.util.List;

public record ChatGptRequest(
        List<ChatGptMessage> messages,
        String model
) {
}
