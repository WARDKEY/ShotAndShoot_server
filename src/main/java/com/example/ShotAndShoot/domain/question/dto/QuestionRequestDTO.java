package com.example.ShotAndShoot.domain.question.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class QuestionRequestDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String category;
}
