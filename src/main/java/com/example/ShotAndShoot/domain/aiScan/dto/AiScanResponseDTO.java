package com.example.ShotAndShoot.domain.aiScan.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class AiScanResponseDTO {

    private List<PredictionDTO> predictions;
    private String imgUrl;

    public AiScanResponseDTO(List<PredictionDTO> predictions, String imgUrl) {
        this.predictions = predictions;
        this.imgUrl = imgUrl;
    }

    @Builder
    @Getter
    public static class PredictionDTO{
        String category;
        int count;
        String wasteSortingInfo;
    }
}
