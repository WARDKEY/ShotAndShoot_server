package com.example.ShotAndShoot.domain.comment.service;

import com.example.ShotAndShoot.domain.comment.dto.CommentRequestDTO;
import com.example.ShotAndShoot.domain.comment.dto.CommentResponseDTO;
import com.example.ShotAndShoot.domain.comment.repository.CommentRepository;
import com.example.ShotAndShoot.domain.member.repository.MemberRepository;
import com.example.ShotAndShoot.domain.member.service.MemberService;
import com.example.ShotAndShoot.domain.question.repository.QuestionRepository;
import com.example.ShotAndShoot.global.entity.Comment;
import com.example.ShotAndShoot.global.entity.Member;
import com.example.ShotAndShoot.global.entity.Question;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @Transactional
    public String saveComment(Long questionId, CommentRequestDTO commentRequestDTO) {

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException(questionId + "번 question이 존재하지 않습니다"));

        // 현재 로그인한 멤버 아이디
        Member member = memberRepository.findById(memberService.getLoginMemberId())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 카카오 아이디가 존재하지 않습니다."));

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
        log.info("조회할 질문 ID: {}", questionId);
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException(questionId + "번 question이 존재하지 않습니다"));

        // 현재 로그인한 회원
        Optional<Member> member = memberRepository.findById(memberService.getLoginMemberId());

        return commentRepository.findAllByQuestion(question).stream()
                .map(comment -> {
                    boolean isAuthor = false;
                    if (member.isEmpty() || !comment.getMember().getMemberId().equals(member.get().getMemberId())) {
                        return new CommentResponseDTO(comment, isAuthor);
                    } else {
                        isAuthor = true;
                        return new CommentResponseDTO(comment, isAuthor);
                    }
                })
                .toList();
    }

    @Transactional
    public String deleteComment(Long commentId) {
        // 삭제할 댓글
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다."));

        // 현재 로그인 한 사용자
        Member member = memberRepository.findById(memberService.getLoginMemberId())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 카카오 아이디가 존재하지 않습니다."));

        // 로그인한 사용자 아이디
        Long memberId = member.getMemberId();

        // 로그인한 사용자와 삭제 버튼을 누르면 사용자의 아이디가 다르면 삭제 불가
        if (!comment.getMember().getMemberId().equals(memberId)) {
            throw new IllegalArgumentException("댓글 작성자만 삭제할 수 있습니다.");
        }

        commentRepository.deleteById(commentId);

        return "댓글 삭제 완료.";
    }

    @Transactional(readOnly = true)
    public String getUserIdFromCommentId(Long commentId) {
        // comment 찾고
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다."));

        // 해당 member의 userId 뽑음
        return comment.getMember().getId();
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDTO> getMyAllComments() {
        // 현재 로그인한 사용자 정보 불러옴
        Member member = memberRepository.findById(memberService.getLoginMemberId())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 로그인을 해주세요."));
        // 해당 사용자의 모든 댓글 불러오기
        List<Comment> myComments = commentRepository.findAllByMember(member);

        return myComments.stream().map(comment -> {
            return new CommentResponseDTO(comment, true);
        }).toList();
    }
}
