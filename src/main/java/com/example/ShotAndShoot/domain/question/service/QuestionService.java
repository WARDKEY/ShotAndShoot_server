package com.example.ShotAndShoot.domain.question.service;

import com.example.ShotAndShoot.domain.question.dto.QuestionResponseDTO;
import com.example.ShotAndShoot.domain.question.repository.QuestionRepository;
import com.example.ShotAndShoot.global.entity.Question;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<QuestionResponseDTO> getAllQuestion() {
        return questionRepository.findAllByOrderByCreateAt().stream()
                .map(QuestionResponseDTO::new)
                .collect(Collectors.toList());
    }

    public QuestionResponseDTO getQuestion(Long questionId) {

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("질문을 찾을 수 없습니다."));

        return new QuestionResponseDTO(question);
    }
}
