package com.example.ShotAndShoot.domain.member.service;

import com.example.ShotAndShoot.domain.member.dto.LoginRequestDTO;
import com.example.ShotAndShoot.domain.member.dto.LoginResponseDTO;
import com.example.ShotAndShoot.domain.member.dto.MemberInfoRequestDTO;
import com.example.ShotAndShoot.domain.member.dto.MemberInfoResponseDTO;
import com.example.ShotAndShoot.domain.member.dto.MemberRequestDTO;
import com.example.ShotAndShoot.domain.member.dto.MemberResponseDTO;
import com.example.ShotAndShoot.domain.member.repository.MemberRepository;
import com.example.ShotAndShoot.global.entity.Member;
import com.example.ShotAndShoot.global.jwt.TokenProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public String register(MemberRequestDTO memberRequestDTO) {
        if (memberRepository.findById(memberRequestDTO.getId()).isPresent()) {
            throw new IllegalArgumentException("[ERROR] 이미 존재하는 회원입니다.");
        }

        Member member = Member.builder()
                .id(memberRequestDTO.getId())
                .name(memberRequestDTO.getName())
                .address(memberRequestDTO.getAddress())
                .detailAddress(memberRequestDTO.getDetailAddress())
                .phoneNumber(memberRequestDTO.getPhoneNumber())
                .build();
        memberRepository.save(member);

        return member.getName() + "님 회원가입이 완료되었습니다.";

    }

    @Transactional(readOnly = true)
    public MemberResponseDTO getMember() {
        Member member = memberRepository.findById(getLoginMemberId())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 카카오 아이디가 존재하지 않습니다."));

        return new MemberResponseDTO(member);
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDTO> getAllMember() {
        return memberRepository.findAll().stream()
                .map(MemberResponseDTO::new)
                .toList();

    }

    public LoginResponseDTO getKakao(LoginRequestDTO loginRequestDTO) {
        Member member = memberRepository.findById(loginRequestDTO.getLoginId())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 카카오 아이디가 존재하지 않습니다."));

        member.setRefreshToken(loginRequestDTO.getRefreshToken());
        memberRepository.save(member);

        System.out.println("카카오 로그인 완료");
        return new LoginResponseDTO(loginRequestDTO.getLoginId(), loginRequestDTO.getNickName());

    }

    public LoginResponseDTO getGoogle(LoginRequestDTO loginRequestDTO) {
        Member member = memberRepository.findById(loginRequestDTO.getLoginId())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 구글 아이디가 존재하지 않습니다."));

        member.setRefreshToken(loginRequestDTO.getRefreshToken());
        memberRepository.save(member);

        System.out.println("구글 로그인 완료");
        return new LoginResponseDTO(loginRequestDTO.getLoginId(), loginRequestDTO.getNickName());
    }

    @Transactional
    public String logout() {
        Member member = memberRepository.findById(getLoginMemberId())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 로그아웃에 실패하였습니다."));

        member.setRefreshToken(null);
        memberRepository.save(member);
        SecurityContextHolder.getContext().setAuthentication(null);

        return "로그아웃이 완료되었습니다.";
    }

    @Transactional
    public String unregister() {
        Member member = memberRepository.findById(getLoginMemberId())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 회원이 존재하지 않습니다."));

        memberRepository.delete(member);
        SecurityContextHolder.getContext().setAuthentication(null);

        return "회원 탈퇴가 완료되었습니다.";
    }

    //AccessToken 검증 후 memberId 추출
    public String getLoginMemberId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userId = (String) authentication.getPrincipal();

        return userId;
    }

    //RefreshToken 검증
    public boolean isValidRefreshToken(String userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 사용자가 없습니다."));

        String refreshToken = member.getRefreshToken();

        if (refreshToken != null) {
            if (tokenProvider.validateToken(refreshToken)) {
                return true;
            } else {
                System.out.println("refreshToken만료");
                member.setRefreshToken(null);
                memberRepository.save(member);
                return false;
            }
        }
        return false; // RefreshToken이 없음
    }

    @Transactional
    public String modifyMemberInfo(MemberInfoRequestDTO memberInfoRequestDTO) {
        // 로그인 한 사용자 찾은 뒤
        Member member = memberRepository.findById(getLoginMemberId())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 회원이 존재하지 않습니다."));

        // 사용자 정보 수정
        member.updateName(memberInfoRequestDTO.getNickName());
        member.updateAddress(memberInfoRequestDTO.getAddress());
        member.updatedetailAddress(memberInfoRequestDTO.getDetailAddress());

        memberRepository.save(member);

        return "회원 정보 수정 완료.";
    }

    public MemberInfoResponseDTO getMemberNameAndAddress() {
        Member member = memberRepository.findById(getLoginMemberId())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 회원이 존재하지 않습니다."));
        return new MemberInfoResponseDTO(member.getName(), member.getAddress(), member.getDetailAddress());
    }
}
