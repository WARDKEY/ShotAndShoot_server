package com.example.ShotAndShoot.domain.aiComment.dto;

import com.example.ShotAndShoot.global.entity.AiComment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AiCommentResponseDTO {
    private Long aiCommentId;
    private String content;
    private Long questionId;

    public AiCommentResponseDTO(AiComment aiComment) {
        this.aiCommentId = aiComment.getAiCommentId();
        this.content = aiComment.getContent();
        this.questionId = aiComment.getQuestion().getQuestionId();
    }
}
