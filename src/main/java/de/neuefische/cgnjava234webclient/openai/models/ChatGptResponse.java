package de.neuefische.cgnjava234webclient.openai.models;

import java.util.List;

public record ChatGptResponse(
        String id,
        List<ChatGptChoice> choices
) {
}
