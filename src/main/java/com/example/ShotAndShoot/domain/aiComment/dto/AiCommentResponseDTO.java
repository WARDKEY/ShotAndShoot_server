package com.example.ShotAndShoot.domain.aiComment.dto;

import com.example.ShotAndShoot.global.entity.AiComment;
import com.example.ShotAndShoot.global.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AiCommentResponseDTO {
    private Long aiCommentId;
    private String content;
    private Question question;

    public AiCommentResponseDTO(AiComment aiComment) {
        this.aiCommentId = aiComment.getAiCommentId();
        this.content = aiComment.getContent();
        this.question = aiComment.getQuestion();
    }
}
