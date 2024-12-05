package com.example.ShotAndShoot.domain.aiComment.repository;

import com.example.ShotAndShoot.global.entity.AiComment;
import com.example.ShotAndShoot.global.entity.Question;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AiCommentRepository extends JpaRepository<AiComment, Long> {
    Optional<AiComment> findByQuestion(Question question);
}
