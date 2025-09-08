package com.example.ShotAndShoot.domain.aiScan.controller;


import com.example.ShotAndShoot.domain.aiScan.dto.AiScanResponseDTO;
import com.example.ShotAndShoot.domain.aiScan.service.AiScanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequestMapping("/api/v1/scan")
@RestController
@RequiredArgsConstructor
public class AiScanController {
    private final AiScanService aiScanService;

    @PostMapping("/")
    public ResponseEntity<AiScanResponseDTO> scanWasteImage(@RequestPart MultipartFile file) throws Exception {
        AiScanResponseDTO response = aiScanService.scanWasteImage(file);
        log.info(response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
