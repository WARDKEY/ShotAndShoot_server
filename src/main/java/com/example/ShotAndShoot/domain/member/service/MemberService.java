package com.example.ShotAndShoot.domain.member.service;

import com.example.ShotAndShoot.domain.member.dto.KakaoRequestDTO;
import com.example.ShotAndShoot.domain.member.dto.KakaoResponseDTO;
import com.example.ShotAndShoot.domain.member.dto.MemberRequestDTO;
import com.example.ShotAndShoot.domain.member.dto.MemberResponseDTO;
import com.example.ShotAndShoot.domain.member.repsitory.MemberRepository;
import com.example.ShotAndShoot.global.entity.Member;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public String register(MemberRequestDTO memberRequestDTO) {
        if (memberRepository.findByName(memberRequestDTO.getName()).isPresent()) {
            throw new IllegalArgumentException("[ERROR] 이미 존재하는 회원입니다.");
        }

        Member member = Member.builder()
                .name(memberRequestDTO.getName())
                .address(memberRequestDTO.getAddress())
                .phoneNumber(memberRequestDTO.getPhoneNumber())
                .build();
        memberRepository.save(member);

        return member.getName() + "님 회원가입이 완료되었습니다.";

    }

    @Transactional(readOnly = true)
    public List<MemberResponseDTO> getAllMember() {
        return memberRepository.findAll().stream()
                .map(MemberResponseDTO::new)
                .toList();

    }

    @Transactional
    public String unregister(Long memberId) {
        if (memberRepository.findById(memberId).isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 회원입니다.");
        }

        memberRepository.deleteById(memberId);

        return "회원탈퇴가 완료되었습니다.";
    }

    public KakaoResponseDTO getKakao(KakaoRequestDTO kakaoRequestDTO) {
        if (kakaoRequestDTO.getKakaoId() == null){
            throw new IllegalArgumentException("[ERROR]카카오 아이다가 존재하지 않습니다.");
        }
        return new KakaoResponseDTO(kakaoRequestDTO.getKakaoId(), kakaoRequestDTO.getNickName());
    }
}
