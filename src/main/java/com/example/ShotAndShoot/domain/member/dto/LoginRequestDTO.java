package com.example.ShotAndShoot.domain.member.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String loginId;
    private String nickName;
    private String refreshToken;
}
