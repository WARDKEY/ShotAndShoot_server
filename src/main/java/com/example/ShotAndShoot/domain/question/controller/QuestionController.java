package com.example.ShotAndShoot.domain.question.controller;

import com.example.ShotAndShoot.domain.question.dto.QuestionResponseDTO;
import com.example.ShotAndShoot.domain.question.service.QuestionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/vi/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    /**
     * 모든 질문들 조회
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<QuestionResponseDTO>> getAllQuestion() {
        List<QuestionResponseDTO> questions = questionService.getAllQuestion();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    /**
     * questionId에 해당하는 질문 조회
     * @param questionId
     * @return
     */
    @PostMapping("/{questionId}")
    public ResponseEntity<QuestionResponseDTO> getQuestion(@PathVariable Long questionId) {
        QuestionResponseDTO question = questionService.getQuestion(questionId);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }



}
