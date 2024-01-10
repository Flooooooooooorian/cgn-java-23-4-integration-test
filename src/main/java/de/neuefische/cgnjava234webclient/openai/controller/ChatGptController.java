package de.neuefische.cgnjava234webclient.openai.controller;

import de.neuefische.cgnjava234webclient.openai.service.ChatGPTService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatGptController {

    private final ChatGPTService chatGPTService;

    @PostMapping
    public String chat(@RequestBody String message) {
        return chatGPTService.chatGpt(message);
    }
}
