package com.example.ShotAndShoot.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KakaoResponseDTO {
    private Long kakaoId;
    private String nickName;
}
