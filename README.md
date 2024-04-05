# 영단어 외우기 사이트

예전에 만든 영단어 만들기 사이트 TDD 연습 겸 새롭게 만들어보려고 합니다

## 개요

 - 관리자 기능 (영단어 등록, 엑셀 업로드를 이용한 영단어 일괄등록, 영단어 삭제, 영단어 수정)
 - 사용자 기능 (영단어 조회, 외운 영단어 저장, 외운 영단어 삭제, 외운 영단어 통계 조회)
 - 공통 기능 (로그인-카카오, 네이버, 구글, 로그아웃)

## 프로젝트 사용 기술 스택

Spring boot, JPA, postgresql, H2 사용 예정

## 패키지 구조
헥사고날 아키텍처를 사용해 보려고 함

```
com.example.portfolio
|-- 도메인(단위 별로 구문 ex:word, user, global)
    |-- adapter 
        |-- in
            |-- controller
        |-- out/persistence
            |-- repository(out 포트의 구현체, querydsl 쓸때 사용)
    |-- application
        |-- port (인터페이스)
            |-- in (기능 정의)
            |-- out (db와 통신 위한 기능 정의 jpa 사용)
        |-- service (비즈니스 로직 in 포트의 구현체)
            |-- serviceImpl
    |-- domain (엔티티)
        |-- entity.java
```

## 데이터베이스 모델 (ERD)

![스크린샷 2024-01-01 오후 10 09 08](https://github.com/ejoongseok/product-order-service/assets/45224987/d65db7f1-eb1d-4ff1-ae31-5197be3b7c06)


## API 명세 (SWAGGER 사용)

<img width="1438" alt="스크린샷 2024-01-19 오후 5 36 33" src="https://github.com/sky7214sky72/portfolio/assets/123547317/8fd2fc18-1a98-4b23-8550-bbc6f89662da">
<img width="1428" alt="스크린샷 2024-01-19 오후 5 36 43" src="https://github.com/sky7214sky72/portfolio/assets/45224987/fe76c716-db77-49e5-80b4-25d9dbf4a87b">

## 모니터링 툴 (prometheus, promtail, loki, grafana)
### 서버 로그나 현재 서버 상태 파악 가능한 모니터링 툴 적용
![image](https://github.com/sky7214sky72/portfolio/assets/45224987/1c4e01ac-ba48-4b4a-b740-8106ac23785b)
![image](https://github.com/sky7214sky72/portfolio/assets/45224987/e327c16b-4c5b-4670-b6c9-4aade9f88ba2)

