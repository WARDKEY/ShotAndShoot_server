package com.example.ShotAndShoot.domain.comment.service;

import com.example.ShotAndShoot.domain.comment.dto.CommentRequestDTO;
import com.example.ShotAndShoot.domain.comment.dto.CommentResponseDTO;
import com.example.ShotAndShoot.domain.comment.repository.CommentRepository;
import com.example.ShotAndShoot.domain.member.repsitory.MemberRepository;
import com.example.ShotAndShoot.domain.question.repository.QuestionRepository;
import com.example.ShotAndShoot.global.entity.Comment;
import com.example.ShotAndShoot.global.entity.Member;
import com.example.ShotAndShoot.global.entity.Question;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public String saveComment(Long questionId, CommentRequestDTO commentRequestDTO) {

        Question question = questionRepository.findById(questionId)
                .orElseThrow(()-> new EntityNotFoundException(questionId + "번 question이 존재하지 않습니다"));

        // 임시 멤버값, 추후 DB에서 현재 로그인한 사용자를 가져와야 됨
        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 로그인을 해주세요."));

        Comment comment = Comment.builder()
                .content(commentRequestDTO.getComment())
                .question(question)
                .member(member)
                .build();

        commentRepository.save(comment);

        return "댓글 작성 완료.";
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDTO> getComments(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(()-> new EntityNotFoundException(questionId + "번 question이 존재하지 않습니다"));

        return commentRepository.findAllByQuestion(question).stream()
                .map(CommentResponseDTO::new)
                .toList();
    }

    @Transactional
    public String deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);

        return "댓글 삭제 완료.";
    }

}
