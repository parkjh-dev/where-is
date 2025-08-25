# Where Is - 위치 기반 서비스 플랫폼

## 프로젝트 소개
Where Is는 사용자들이 주변의 흡연 구역, 화장실, 쓰레기통 등의 위치 정보를 공유하고 찾을 수 있는 위치 기반 서비스 플랫폼입니다.

## 기술 스택

### Backend
- **Java 17**
- **Spring Boot 3.x**
- **Spring Security**
- **Spring Data JPA**
- **QueryDSL**
- **MySQL 8.0**
- **Gradle**

### Frontend
- **React 18**
- **Vite**
- **ESLint**

### Infrastructure
- **Docker**
- **Docker Compose**

## 프로젝트 구조
```
where_is/
├── api/where_is/          # Spring Boot 백엔드
├── web/where_is/          # React 프론트엔드
├── db/                    # 데이터베이스 관련 파일
├── infra/                 # 인프라 설정
└── doc/                   # 문서
```

## 설치 및 실행 방법

### 1. 데이터베이스 실행
```bash
cd db
docker-compose up -d
```

### 2. 백엔드 실행
```bash
cd api/where_is
./gradlew bootRun
```

### 3. 프론트엔드 실행
```bash
cd web/where_is
npm install
npm run dev
```

## API 문서
- Swagger UI: http://localhost:8080/swagger-ui.html

## 주요 기능
- 사용자 인증 및 권한 관리
- 흡연 구역 정보 관리
- 화장실 위치 정보 관리
- 쓰레기통 위치 정보 관리
- 댓글 및 좋아요 기능
- 신고 및 요청 기능

## 라이선스
이 프로젝트는 MIT 라이선스 하에 배포됩니다.
