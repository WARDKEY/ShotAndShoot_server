package com.example.ShotAndShoot.domain.aiScan.service;

import com.example.ShotAndShoot.domain.aiScan.dto.AiScanResponseDTO;
import com.example.ShotAndShoot.domain.aiScan.dto.AiScanResponseDTO.PredictionDTO;
import com.example.ShotAndShoot.domain.waste.repository.WasteRepository;
import com.example.ShotAndShoot.global.entity.Waste;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AiScanService {
    private final WasteRepository wasteRepository;
    @Value("${FASTAPI_URL}")
    private String fastApiUrl;

    private String getBase64String(MultipartFile multipartFile) throws Exception {
        byte[] bytes = multipartFile.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }

    @Transactional
    public AiScanResponseDTO scanWasteImage(MultipartFile file) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        String imageFileString = getBase64String(file);
        Map<String, String> body = Map.of("image", imageFileString);

        HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);

        ResponseEntity<Map> response = restTemplate.exchange(
                fastApiUrl,
                HttpMethod.POST,
                requestMessage,
                Map.class
        );

        List<Map<String, Object>> predictionsData = (List<Map<String, Object>>) response.getBody().get("predictions");
        List<PredictionDTO> predictions = new ArrayList<>();

        for (Map<String, Object> prediction : predictionsData) {
            String category = (String) prediction.get("class");
            Double reliability = (Double) prediction.get("reliability");

            Waste waste = wasteRepository.findByWasteName(category)
                    .orElseThrow(() -> new IllegalArgumentException("[ERROR] 폐기물 종류를 찾을 수 없습니다."));

            PredictionDTO predictionDTO = PredictionDTO.builder()
                    .category(category)
                    .reliability(reliability)
                    .wasteSortingInfo(waste.getWasteSortingInfo())
                    .build();

            predictions.add(predictionDTO);
        }

        return new AiScanResponseDTO(predictions, (String) response.getBody().get("img"));
    }
}
