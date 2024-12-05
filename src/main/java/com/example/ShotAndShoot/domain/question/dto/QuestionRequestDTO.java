package com.example.ShotAndShoot.domain.question.dto;

import lombok.Data;

@Data
public class QuestionRequestDTO {
    private String title;
    private String content;
    private String category;
}
