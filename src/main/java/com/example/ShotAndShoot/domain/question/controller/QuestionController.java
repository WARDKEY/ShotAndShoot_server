package com.example.ShotAndShoot.domain.question.controller;

import com.example.ShotAndShoot.domain.question.dto.QuestionRequestDTO;
import com.example.ShotAndShoot.domain.question.dto.QuestionResponseDTO;
import com.example.ShotAndShoot.domain.question.service.QuestionService;
import com.example.ShotAndShoot.global.dto.ResultMessageDTO;
import com.example.ShotAndShoot.global.entity.Question;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    /**
     * Question 작성
     *
     * @param questionRequestDTO
     */
    @PostMapping("/")
    public ResponseEntity<QuestionResponseDTO> saveQuestion(@RequestBody @Valid QuestionRequestDTO questionRequestDTO) {
        Question question = questionService.saveQuestion(questionRequestDTO);
        return new ResponseEntity<>(new QuestionResponseDTO(question), HttpStatus.OK);
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
     * 마이페이지 사용자가 작성한 질문 조회
     *
     * @return
     */
    @GetMapping("/my")
    public ResponseEntity<List<QuestionResponseDTO>> getMyAllQuestion() {
        List<QuestionResponseDTO> myQuestions = questionService.getMyAllQuestion();
        return new ResponseEntity<>(myQuestions, HttpStatus.OK);
    }

    /**
     * 조회수를 기준으로 인기글 내림차순 정렬 및 조회
     *
     * @return
     */
    @GetMapping("/popular")
    public ResponseEntity<List<QuestionResponseDTO>> getQuestionsSortedByPopularity() {
        List<QuestionResponseDTO> popularQuestions = questionService.getQuestionsSortedByPopularity();
        return new ResponseEntity<>(popularQuestions, HttpStatus.OK);
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

    /**
     * questionId에 해당하는 질문 수정
     *
     * @param questionId
     * @param questionRequestDTO
     * @return
     */
    @PutMapping("/{questionId}")
    public ResponseEntity<ResultMessageDTO> updateQuestion(@PathVariable Long questionId,
                                                           @RequestBody @Valid QuestionRequestDTO questionRequestDTO) {
        String message = questionService.updateQuestion(questionId, questionRequestDTO);
        return new ResponseEntity<>(new ResultMessageDTO(message), HttpStatus.OK);
    }

    /**
     * questionId에 해당하는 질문 삭제
     *
     * @param questionId
     * @return
     */
    @DeleteMapping("/{questionId}")
    public ResponseEntity<ResultMessageDTO> deleteQuestion(@PathVariable Long questionId) {
        String message = questionService.deleteQuestion(questionId);
        return new ResponseEntity<>(new ResultMessageDTO(message), HttpStatus.OK);
    }
}
