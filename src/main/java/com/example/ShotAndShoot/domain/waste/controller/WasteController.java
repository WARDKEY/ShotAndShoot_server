package com.example.ShotAndShoot.domain.waste.controller;

import com.example.ShotAndShoot.domain.waste.dto.WasteResponseDTO;
import com.example.ShotAndShoot.domain.waste.service.WasteService;
import com.example.ShotAndShoot.global.entity.Waste;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/v1/waste")
@RestController
@RequiredArgsConstructor
public class WasteController {

    private final WasteService wasteService;

    /**
     * 폐기물 정보 조회
     */
    @GetMapping("/")
    public ResponseEntity<List<WasteResponseDTO>> getAllWaste() {
        List<WasteResponseDTO> wastes = wasteService.getAllWaste();
        return new ResponseEntity<>(wastes, HttpStatus.OK);
    }

    /**
     * 특정 폐기물 정보 조회
     *
     * @return
     */
    @GetMapping("/{wasteName}")
    public ResponseEntity<WasteResponseDTO> getSortedWaste(@PathVariable String wasteName) {
        Waste sortedWaste = wasteService.getSortedWaste(wasteName);
        return new ResponseEntity<>(new WasteResponseDTO(sortedWaste), HttpStatus.OK);
    }
}
