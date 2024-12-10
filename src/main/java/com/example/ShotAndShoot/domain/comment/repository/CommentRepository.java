package com.example.ShotAndShoot.domain.comment.repository;

import com.example.ShotAndShoot.global.entity.Comment;
import com.example.ShotAndShoot.global.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByQuestion(Question question);
}