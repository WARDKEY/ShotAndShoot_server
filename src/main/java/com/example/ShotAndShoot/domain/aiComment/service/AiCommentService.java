package com.example.ShotAndShoot.domain.aiComment.service;

import com.example.ShotAndShoot.domain.aiComment.dto.AiCommentResponseDTO;
import com.example.ShotAndShoot.domain.aiComment.repository.AiCommentRepository;
import com.example.ShotAndShoot.domain.question.repository.QuestionRepository;
import com.example.ShotAndShoot.global.entity.AiComment;
import com.example.ShotAndShoot.global.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AiCommentService {

    private final OllamaChatModel chatModel;
    private final AiCommentRepository aiCommentRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public String saveAiComment(Long questionId) {

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 질문을 찾을 수 없습니다."));

        String comment = chatModel.call(question.getContent());

        AiComment aiComment = AiComment.builder()
                .question(question)
                .content(comment)
                .build();

        aiCommentRepository.save(aiComment);

        return "Ai 댓글 작성 완료.";
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
