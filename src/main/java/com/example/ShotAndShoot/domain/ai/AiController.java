package com.example.ShotAndShoot.domain.ai;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/ai")
@RestController
@RequiredArgsConstructor
public class AiController {
    //    추후 openai 사용시 ChatModel로 변경
    private final OllamaChatModel chatModel;

    @PostMapping("/test")
    public String test(@RequestBody String prompt) {

        return chatModel.call(prompt);
    }

}
