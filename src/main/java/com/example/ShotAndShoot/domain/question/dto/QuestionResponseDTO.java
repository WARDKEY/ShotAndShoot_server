package com.example.ShotAndShoot.domain.question.dto;

import com.example.ShotAndShoot.global.entity.Question;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponseDTO {

    private Long questionId;
    private String title;
    private String content;
    private String category;
    private Integer view;
    private String member;
    private LocalDateTime createAt;

    public QuestionResponseDTO(Question question) {
        this.questionId = question.getQuestionId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.category = question.getCategory();
        this.view = question.getView();
        this.member = question.getMember().getName();
        this.createAt = question.getCreateAt();
    }
}
