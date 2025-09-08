package com.example.ShotAndShoot.domain.aiComment.controller;

import com.example.ShotAndShoot.domain.aiComment.dto.AiCommentResponseDTO;
import com.example.ShotAndShoot.domain.aiComment.service.AiCommentService;
import com.example.ShotAndShoot.global.dto.ResultMessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@Slf4j
@RequestMapping("/api/v1/ai")
@RestController
@RequiredArgsConstructor
public class AiCommentController {
    //    추후 openai 사용시 ChatModel로 변경

    private final AiCommentService aiService;

    private final OllamaChatModel chatModel;

    /**
     * Ai 댓글 생성
     * 사실상 안 씀
     *
     * @param questionid
     */
    /*
    @PostMapping("/{questionid}")
    public ResponseEntity<ResultMessageDTO> saveAiComment(@PathVariable Long questionid) throws ExecutionException, InterruptedException {

        String message = aiService.generateAiComment(questionid).get();
        log.info(message);
        return new ResponseEntity<>(new ResultMessageDTO(message), HttpStatus.OK);
    }
   */

    /**
     * question에 해당하는 Ai 댓글 조회
     *
     * @param questionId
     */
    @GetMapping("/{questionId}")
    public ResponseEntity<AiCommentResponseDTO> getAiComment(@PathVariable Long questionId) {
        AiCommentResponseDTO response = aiService.getAiComment(questionId);
        log.info(response.getContent());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
