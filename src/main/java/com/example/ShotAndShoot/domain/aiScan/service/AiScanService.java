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

import java.util.*;

@Service
@RequiredArgsConstructor
public class AiScanService {
    private final WasteRepository wasteRepository;
    @Value("${FASTAPI_URL}")
    private String fastApiUrl;

    Map<String, String> dataMap = new HashMap<String, String>() {
        {
            put("paper", "종이");
            put("plastic", "플라스틱");
            put("metal", "고철");
            put("glass", "유리");
            put("can", "캔");
            put("styrofoam", "스티로폼");
            put("clothes", "의류");
            put("vinyl", "비닐");
        }
    };

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
            Integer count = (Integer) prediction.get("count");

            Waste waste = wasteRepository.findByWasteName(dataMap.get(category))
                    .orElseThrow(() -> new IllegalArgumentException("[ERROR] 폐기물 종류를 찾을 수 없습니다."));

            PredictionDTO predictionDTO = PredictionDTO.builder()
                    .category(dataMap.get(category))
                    .count(count)
                    .wasteSortingInfo(waste.getWasteSortingInfo())
                    .build();

            predictions.add(predictionDTO);
        }

        return new AiScanResponseDTO(predictions, (String) response.getBody().get("img"));
    }
}
