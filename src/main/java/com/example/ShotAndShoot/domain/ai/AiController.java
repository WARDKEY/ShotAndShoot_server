package com.example.ShotAndShoot.domain.ai;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/v1/ai")
@RestController
@RequiredArgsConstructor
public class AiController {
    //    추후 openai 사용시 ChatModel로 변경
    private final OllamaChatModel chatModel;

    @PostMapping("/test")
    public String test(@RequestBody String prompt) {

        log.info("prompt = " + prompt);

        String response = chatModel.call(prompt);

        log.info(response);

        return response;
    }
}
