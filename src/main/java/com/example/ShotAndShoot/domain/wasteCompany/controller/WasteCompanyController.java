package com.example.ShotAndShoot.domain.wasteCompany.controller;

import com.example.ShotAndShoot.domain.wasteCompany.dto.WasteCompanyResponseDTO;
import com.example.ShotAndShoot.domain.wasteCompany.service.WasteCompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/api/v1/wasteCompany")
@RestController
@RequiredArgsConstructor
public class WasteCompanyController {

    private final WasteCompanyService wasteCompanyService;

    /**
     * 폐기물업체 조회
     */
    @GetMapping("/")
    public ResponseEntity<List<WasteCompanyResponseDTO>> getAllWasteCompany() {
        List<WasteCompanyResponseDTO> wasteCompanies = wasteCompanyService.getAllWasteCompany();
        return new ResponseEntity<>(wasteCompanies, HttpStatus.OK);
    }
}
