package com.example.ShotAndShoot.domain.comment.dto;

import com.example.ShotAndShoot.global.entity.Comment;
import com.example.ShotAndShoot.global.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDTO {

    private Long commentId;
    private String content;
    private Long questionId;
    private String memberId;
    private LocalDateTime createdAt;

    public CommentResponseDTO(Comment comment) {
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
        this.questionId = comment.getQuestion().getQuestionId();
        this.memberId = comment.getMember().getName();
        this.createdAt = comment.getCreateAt();
    }
}
