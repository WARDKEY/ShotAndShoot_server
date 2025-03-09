# Shot&Shoot_Server
### Shot&Shoot - 쓰레기 분리배출을 도와주는 앱 서비스  Server Repo.

<div align="center">

![image](https://github.com/user-attachments/assets/dc291b50-af8f-4b48-8f0f-70b2c43cd480)


[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2FOutDecision%2FOutDecision_Server.git&count_bg=%2379C83D&title_bg=%23555555&icon=&icon_color=%23E7E7E7&title=hits&edge_flat=false)](https://hits.seeyoufarm.com)

</div>

> ## 개발기간
**2024.12 ~ 2025.03**

> ## 팀원 소개

<table>
  <tbody>
    <tr>
      <td align="center"><a href="https://github.com/tgy1201"><img src="https://avatars.githubusercontent.com/tgy1201" width="100px;" alt="유태근"/><br /><sub><b>유태근</b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/WARDKEY"><img src="https://avatars.githubusercontent.com/WARDKEY" width="100px;" alt="서근엽"/><br /><sub><b>서근엽</b></sub></a><br /></td>
    </tr>
  </tbody>
</table><br/>

## 프로젝트 소개
### 프로젝트명 :  Shot&Shoot

“Shot&Shoot”은 폐기물 사진을 찍으면 종류와 분립배출 방법을 알려주는 앱입니다.
사용자는 커뮤니티에서 폐기물 관련 질문을 공유하고, 가까운 분리수거 대행업체도 확인할 수 있습니다.

💡 ***Q. Shot&Shoot의 의미?***

폐기물 사진을 찍고(Shot), 분리수거함에 넣는다(Shoot) 라는 의미로 "Shot&Shoot"이라고 정했습니다.

---

## Stacks 

**ENVIRONMENT**

<img src="https://img.shields.io/badge/visual studio code-007ACC?style=for-the-badge&logo=visualstudio&logoColor=white"> 	![Android Studio](https://img.shields.io/badge/android%20studio-346ac1?style=for-the-badge&logo=android%20studio&logoColor=white) <img src="https://img.shields.io/badge/intellijidea-000000?style=for-the-badge&logo=intellijidea&logoColor=white"> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">

**FRONTEND** 

![Dart](https://img.shields.io/badge/dart-%230175C2.svg?style=for-the-badge&logo=dart&logoColor=white) ![Flutter](https://img.shields.io/badge/Flutter-%2302569B.svg?style=for-the-badge&logo=Flutter&logoColor=white)


**BACKEND**

<img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=Java&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"> ![MariaDB](https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=mariadb&logoColor=white)

**AI**

![Python](https://img.shields.io/badge/python-3670A0?style=for-the-badge&logo=python&logoColor=ffdd54) ![FastAPI](https://img.shields.io/badge/FastAPI-005571?style=for-the-badge&logo=fastapi)


**COMMUNICATION**

<img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white"> <img src="https://img.shields.io/badge/Discord-5865F2?style=for-the-badge&logo=Discord&logoColor=white"> 

---
## 화면 구성 📺
| 메인 화면  |  폐기물 상세 화면   |
| :-------------------------------------------: | :------------: |
|  ![image](https://github.com/user-attachments/assets/f9c6fad9-21a0-4acf-b212-e737ec36a4d4) | ![image](https://github.com/user-attachments/assets/662bfffa-a7ba-458b-b93c-946fe49b3066) |
| 게시판 화면  |  게시글 상세 화면  |  
| ![image](https://github.com/user-attachments/assets/a5ac95b0-9484-45a7-bf80-6de05535cacf) | ![image](https://github.com/user-attachments/assets/7259ef0d-2ace-41df-a9c5-0bee82d520b9) |
| 폐기물 스캔 화면 | 스캔 결과 화면 |
| ![image](https://github.com/user-attachments/assets/af8fc919-990a-4e53-b391-5d72c127fba3) | ![image](https://github.com/user-attachments/assets/59596977-c6e9-4052-9c50-169e1e68d661) |
| 폐기물 업체 화면 | 폐기물 업체 선택 화면 |
| ![image](https://github.com/user-attachments/assets/ea16c602-8039-4306-bbe5-8c6740ea6959) | ![image](https://github.com/user-attachments/assets/3a1afe22-0b65-41cd-8130-b4321ee0c5c5) |
| 로그인 화면 | 회원가입 화면 |
| ![image](https://github.com/user-attachments/assets/b0cdbee4-8c6c-4c0f-8a6a-9d5380178fe7) | |
| 마이 페이지 화면 | 회원 정보 수정 화면    |  
|  | ![image](https://github.com/user-attachments/assets/44885068-08d4-4b96-bd4d-0fb961a5b325) |



---
## 주요 기능 📦

### ⭐️ 투표 기능
- 게시판/게시글 상세 조회 페이지에서 빠른 투표 가능
- 옵션별 투표 결과 백분율로 노출
- 투표 게시글 등록 시 옵션에 사진 첨부 가능

### ⭐️ 커뮤니티 기능
- 댓글/좋아요/게시글 끌어올리기 기능

### ⭐️ 카테고리 및 검색 필터
- 7개의 카테고리로 게시글 분류 가능
- 투표 관련 성별 및 투표 상태 필터
- 게시글 제목/내용/옵션에서 키워드 검색 가능

### ⭐️ 이용자 보상 기능
- 포인트 제도
- 일정 조건 이상 달성 시 칭호 획득 및 장착 가능

### ⭐️ 사용자 맞춤 게시글 추천 서비스
- 사용자 협업 필터링을 이용해 비슷한 사용자를 분석하여 사용자가 관심있을만한 주제의 게시글을 추천

---
