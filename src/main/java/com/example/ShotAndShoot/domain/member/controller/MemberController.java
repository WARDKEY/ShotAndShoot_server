package com.example.ShotAndShoot.domain.member.controller;

import com.example.ShotAndShoot.domain.member.dto.LoginRequestDTO;
import com.example.ShotAndShoot.domain.member.dto.LoginResponseDTO;
import com.example.ShotAndShoot.domain.member.dto.MemberInfoRequestDTO;
import com.example.ShotAndShoot.domain.member.dto.MemberInfoResponseDTO;
import com.example.ShotAndShoot.domain.member.dto.MemberRequestDTO;
import com.example.ShotAndShoot.domain.member.dto.MemberResponseDTO;
import com.example.ShotAndShoot.domain.member.dto.UserIdResponseDTO;
import com.example.ShotAndShoot.domain.member.service.MemberService;
import com.example.ShotAndShoot.global.dto.ResultMessageDTO;
import com.example.ShotAndShoot.global.jwt.TokenProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/v1/member")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final TokenProvider tokenProvider;
    private final MemberService memberService;

    /**
     * 회원가입, 시큐리티 적용 전 임시 데이터 저장용
     *
     * @param memberRequestDTO
     */
    @PostMapping("/register")
    public ResponseEntity<ResultMessageDTO> register(@RequestBody MemberRequestDTO memberRequestDTO) {
        String message = memberService.register(memberRequestDTO);
        return new ResponseEntity<>(new ResultMessageDTO(message), HttpStatus.OK);
    }

    /**
     * 카카오 로그인 사용자 정보 받기
     *
     * @param loginRequestDTO
     * @return
     */
    @PostMapping("/kakaoLogin")
    public ResponseEntity<LoginResponseDTO> getKakaoId(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            String accessToken = tokenProvider.createAccessToken(loginRequestDTO.getLoginId());
            String refreshToken = tokenProvider.createRefreshToken();

            loginRequestDTO.setRefreshToken(refreshToken);

            LoginResponseDTO kakao = memberService.getKakao(loginRequestDTO);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + accessToken);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(kakao);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.CREATED); // 201
        }
    }

    /**
     * 구글 로그인 사용자 정보 받기
     *
     * @param loginRequestDTO
     * @return
     */
    @PostMapping("/googleLogin")
    public ResponseEntity<LoginResponseDTO> getGoogleId(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            String accessToken = tokenProvider.createAccessToken(loginRequestDTO.getLoginId());
            String refreshToken = tokenProvider.createRefreshToken();

            loginRequestDTO.setRefreshToken(refreshToken);

            LoginResponseDTO google = memberService.getGoogle(loginRequestDTO);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + accessToken);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(google);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.CREATED); // 201
        }
    }

    /**
     * 회원 조회[테스트]
     *
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<MemberResponseDTO> getMember() {
        MemberResponseDTO member = memberService.getMember();
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    /**
     * 모든 회원 단순 조회
     *
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<List<MemberResponseDTO>> getAllMember() {
        List<MemberResponseDTO> members = memberService.getAllMember();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    /**
     * 회원 로그아웃
     *
     * @return
     */
    @PostMapping("/logout")
    public ResponseEntity<ResultMessageDTO> logout() {
        String message = memberService.logout();
        return new ResponseEntity<>(new ResultMessageDTO(message), HttpStatus.OK);
    }

    /**
     * 회원탈퇴, 수정필요
     */
    @DeleteMapping("/unregister")
    public ResponseEntity<ResultMessageDTO> unregister() {
        String message = memberService.unregister();
        return new ResponseEntity<>(new ResultMessageDTO(message), HttpStatus.OK);
    }

    /**
     * 현재 로그인한 member의 userId 조회
     *
     * @return
     */
    @GetMapping("/user")
    public ResponseEntity<UserIdResponseDTO> getUserId() {
        String userId = memberService.getLoginMemberId();
        return new ResponseEntity<>(new UserIdResponseDTO(userId), HttpStatus.OK);
    }

    /**
     * 마이페이지 회원 정보 수정
     *
     * @param memberInfoRequestDTO
     * @return
     */
    @PutMapping("/modify")
    public ResponseEntity<ResultMessageDTO> modifyMemberInfo(@RequestBody
                                                             MemberInfoRequestDTO memberInfoRequestDTO) {
        String result = memberService.modifyMemberInfo(memberInfoRequestDTO);

        return new ResponseEntity<>(new ResultMessageDTO(result), HttpStatus.OK);
    }

    /**
     * 회원 이름과 주소 조회
     * @return
     */
    @GetMapping("/info")
    public ResponseEntity<MemberInfoResponseDTO> getMemberNameAndAddress() {
        MemberInfoResponseDTO memberInfoResponseDTO = memberService.getMemberNameAndAddress();
        return new ResponseEntity<>(memberInfoResponseDTO, HttpStatus.OK);
    }
}
