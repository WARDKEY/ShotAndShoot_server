package com.example.ShotAndShoot.domain.comment.dto;

import com.example.ShotAndShoot.global.entity.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String memberName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

    public CommentResponseDTO(Comment comment) {
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
        this.questionId = comment.getQuestion().getQuestionId();
        this.memberName = comment.getMember().getName();
        this.createdAt = comment.getCreateAt();
    }
}
