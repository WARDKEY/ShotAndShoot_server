package com.example.ShotAndShoot.domain.question.controller;

import com.example.ShotAndShoot.domain.question.dto.QuestionRequestDTO;
import com.example.ShotAndShoot.domain.question.dto.QuestionResponseDTO;
import com.example.ShotAndShoot.domain.question.service.QuestionService;
import com.example.ShotAndShoot.global.dto.ResultMessageDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    /**
     * Question 작성, 로그인 정보로부터 멤버 불러오는 부분 추가해야됨
     *
     * @param questionRequestDTO
     */
    @PostMapping("/")
    public ResponseEntity<ResultMessageDTO> saveQuestion(@RequestBody QuestionRequestDTO questionRequestDTO) {
        String message = questionService.saveQuestion(questionRequestDTO);
        return new ResponseEntity<>(new ResultMessageDTO(message), HttpStatus.OK);
    }

    /**
     * 모든 질문들 조회
     */
    @GetMapping("/")
    public ResponseEntity<List<QuestionResponseDTO>> getAllQuestion() {
        List<QuestionResponseDTO> questions = questionService.getAllQuestion();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    /**
     * questionId에 해당하는 질문 조회
     *
     * @param questionId
     * @return
     */
    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionResponseDTO> getQuestion(@PathVariable Long questionId) {
        QuestionResponseDTO question = questionService.getQuestion(questionId);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }
}
