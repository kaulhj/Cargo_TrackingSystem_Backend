# 화물추적 시스템

---

(주)씨에어허브 물류기업 과 함께한 산학협력 프로젝트로, 화물 운송 중 측정한 데이터 및 날씨, 운송정보등을 앱으로 측정하여 웹으로 시각화 한 프로젝트입니다.

### 참여인원(4명)

- 기획 : 2명
- Android : 1명
- WebFullStack : 1명(본인)

### 본인 기여한 역할

Web FullStack Developer

2022.09 ~ 2022.12

사용한 기술

- Springboot를 이용해 필요한 api 개발
- AWS 기반 아키텍처 설계 및 구축
- 도메인 연결 및 https 적용
- FrontEnd 화면 개발, Netlify를 통한 웹 배포
- AWS-LAMDA,API-GATEWAY를 이용한 공공 날씨 이용 API 개발

- Backend
    - SpringBoot-JDBC, MYBATIS
    - Linux-Ubuntu
- DevOps
    - AWS EC2, RDS, S3, Route53, Lamda, API-Gateway
- Frontend
    - HTML, CSS, JAVASCRIPT
    - BootStrap
    - Netlify(웹 배포)

---

## 프로젝트 전체적인 구조

![image](https://user-images.githubusercontent.com/89002687/215186767-19fecb4a-2905-4f94-a3e1-b22ca2ce1d30.png)

### Sequence Diagram

![Untitled](%E1%84%92%E1%85%AA%E1%84%86%E1%85%AE%E1%86%AF%E1%84%8E%E1%85%AE%E1%84%8C%E1%85%A5%E1%86%A8%20%E1%84%89%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%A6%E1%86%B7%206dcf5aa4e0e7498dbb7ba7a1ef5fb87d/Untitled%201.png)

- 실제 화물기사님이 화물차량을 이용해 운송을 시작하실 때 안드로이드 어플리케이션을 통해 로그인 및 측정을 시작 하고, 운행 종료 시점까지 일정간격, 약 15분에 한번씩 실시간 위치, 날씨, 운송정보가 기록되고 종료버튼을 누를 시 측정이 종료됩니다.
- 측정되는 과정에서는 웹서버를 통해 날씨를 조회하고, Reverse GeoCoding(역지오코딩)을 통해서 공공 API를 통해 날씨정보와 위도, 경도 input값을 통한 실제 주소 위치정보를 반환 받습니다. 이를 Database에 저장 합니다.
- 웹 어플리케이션을 통해 Database를 통해 측정된 Data정보를 요청하여 이를 KAKAO-MAP API를 이용해 시각화에 사용합니다.

## WBS(작업 분할 구조도)

![Untitled](%E1%84%92%E1%85%AA%E1%84%86%E1%85%AE%E1%86%AF%E1%84%8E%E1%85%AE%E1%84%8C%E1%85%A5%E1%86%A8%20%E1%84%89%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%A6%E1%86%B7%206dcf5aa4e0e7498dbb7ba7a1ef5fb87d/Untitled%202.png)

## 사용 기술 및 환경

---

Spring boot, Gradle, Mybatis, MySQL, Java11, AWS Cloud, Linux-Ubuntu,Html,CSS,JavaScript,Netlify

## 화면설계

---

![Untitled](%E1%84%92%E1%85%AA%E1%84%86%E1%85%AE%E1%86%AF%E1%84%8E%E1%85%AE%E1%84%8C%E1%85%A5%E1%86%A8%20%E1%84%89%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%A6%E1%86%B7%206dcf5aa4e0e7498dbb7ba7a1ef5fb87d/Untitled%203.png)

## 프로젝트 DB ERD

---

![Untitled](%E1%84%92%E1%85%AA%E1%84%86%E1%85%AE%E1%86%AF%E1%84%8E%E1%85%AE%E1%84%8C%E1%85%A5%E1%86%A8%20%E1%84%89%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%A6%E1%86%B7%206dcf5aa4e0e7498dbb7ba7a1ef5fb87d/Untitled%204.png)
