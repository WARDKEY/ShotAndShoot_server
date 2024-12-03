package com.example.ShotAndShoot.domain.question.repository;

import com.example.ShotAndShoot.global.entity.Question;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findAllByOrderByCreateAt();
}
