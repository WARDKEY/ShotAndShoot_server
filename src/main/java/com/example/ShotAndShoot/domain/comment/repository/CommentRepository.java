package com.example.ShotAndShoot.domain.comment.repository;

import com.example.ShotAndShoot.global.entity.Comment;
import com.example.ShotAndShoot.global.entity.Member;
import com.example.ShotAndShoot.global.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByQuestion(Question question);

    // Question 엔티티를 기준으로 댓글의 개수를 조회하는 메서드
    Integer countByQuestion(Question question);

    // member로 모든 댓글 찾기
    List<Comment> findAllByMember(Member member);
}