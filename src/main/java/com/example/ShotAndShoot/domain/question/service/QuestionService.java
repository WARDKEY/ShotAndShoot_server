package com.example.ShotAndShoot.domain.question.service;

import com.example.ShotAndShoot.domain.aiComment.service.AiCommentService;
import com.example.ShotAndShoot.domain.member.repository.MemberRepository;
import com.example.ShotAndShoot.domain.member.service.MemberService;
import com.example.ShotAndShoot.domain.question.dto.QuestionRequestDTO;
import com.example.ShotAndShoot.domain.question.dto.QuestionResponseDTO;
import com.example.ShotAndShoot.domain.question.repository.QuestionRepository;
import com.example.ShotAndShoot.global.entity.Member;
import com.example.ShotAndShoot.global.entity.Question;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final AiCommentService aiCommentService;

    @Transactional
    public String saveQuestion(QuestionRequestDTO questionRequestDTO) {

        // 현재 로그인한 유저 불러옴
        Member member = memberRepository.findById(memberService.getLoginMemberId())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 로그인을 해주세요."));

        Question question = Question.builder()
                .title(questionRequestDTO.getTitle())
                .content(questionRequestDTO.getContent())
                .category(questionRequestDTO.getCategory())
                .view(0)
                .member(member)
                .build();

        // 질문 저장
        questionRepository.save(question);

        // ai 댓글 생성
        String aiMessage = aiCommentService.saveAiComment(question.getQuestionId());

        log.info("ai 댓글 성공 여부 확인 ==== {}", aiMessage);

        return "질문 작성 완료.";
    }

    @Transactional(readOnly = true)
    public List<QuestionResponseDTO> getAllQuestion() {
        return questionRepository.findAllByOrderByCreateAt().stream()
                .map(QuestionResponseDTO::new)
                .toList();
    }

    @Transactional
    public QuestionResponseDTO getQuestion(Long questionId) {

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 질문을 찾을 수 없습니다."));

        question.updateView();  // 조회수 +1

        log.info(question.getQuestionId().toString());
        System.out.println(question);

        return new QuestionResponseDTO(question);
    }

    @Transactional
    public String updateQuestion(Long questionId, QuestionRequestDTO questionRequestDTO) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(()-> new EntityNotFoundException(questionId + "번 question이 존재하지 않습니다"));

        // 임시 멤버값, 추후 DB에서 현재 로그인한 사용자를 가져와야 됨
        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 로그인을 해주세요."));

        if(!question.getTitle().equals(questionRequestDTO.getTitle())) {
            question.updateTitle(questionRequestDTO.getTitle());
        }

        if(!question.getContent().equals(questionRequestDTO.getContent())) {
            question.updateContent(questionRequestDTO.getContent());
        }

        if(!question.getCategory().equals(questionRequestDTO.getCategory())) {
            question.updateCategory(questionRequestDTO.getCategory());
        }

        questionRepository.save(question);

        return "질문 수정 완료.";
    }

    @Transactional
    public String deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);

        return "질문 삭제 완료.";
    }
}
