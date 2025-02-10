package com.example.ShotAndShoot.domain.question.repository;

import com.example.ShotAndShoot.global.entity.Member;
import com.example.ShotAndShoot.global.entity.Question;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    // 최신 질문이 먼저 오게 내림차순 정렬
    List<Question> findAllByOrderByCreateAtDesc();

    // 사용자가 작성한 질문 조회
    List<Question> findAllByMember(Member member);

    // 조회수가 많은 순으로 내림차순 정렬
    List<Question> findAllByOrderByViewDesc();
}
