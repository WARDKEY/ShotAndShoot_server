package com.example.ShotAndShoot.domain.member.dto;

import com.example.ShotAndShoot.global.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDTO {
    private Long memberId;
    private String name;
    private String address;

    public MemberResponseDTO(Member member) {
        this.memberId = member.getMemberId();
        this.name = member.getName();
        this.address = member.getAddress();
    }
}
