package com.example.ShotAndShoot.domain.aiComment.service;

import com.example.ShotAndShoot.domain.aiComment.dto.AiCommentResponseDTO;
import com.example.ShotAndShoot.domain.aiComment.repository.AiCommentRepository;
import com.example.ShotAndShoot.domain.question.repository.QuestionRepository;
import com.example.ShotAndShoot.global.entity.AiComment;
import com.example.ShotAndShoot.global.entity.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class AiCommentService {

    private final OllamaChatModel chatModel;
    private final AiCommentRepository aiCommentRepository;
    private final QuestionRepository questionRepository;

    @Async
    @Transactional
    public CompletableFuture<String> generateAiComment(Question question) {
        log.info("AI 댓글 생성 시작: questionId={}", question.getQuestionId());

        String comment = chatModel.call(question.getContent());

        AiComment aiComment = AiComment.builder()
                .question(question)
                .content(comment)
                .build();

        aiCommentRepository.save(aiComment);

        return CompletableFuture.completedFuture("AI 댓글 작성 완료.");
    }

    @Transactional(readOnly = true)
    public AiCommentResponseDTO getAiComment(Long questionId) {

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 질문을 찾을 수 없습니다."));

        AiComment aiComment = aiCommentRepository.findByQuestion(question)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] ai 답변을 찾을 수 없습니다."));

        return new AiCommentResponseDTO(aiComment);
    }

}
