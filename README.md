# 🚀 Where Is - 위치 기반 서비스 플랫폼

## 📖 Introduction
Where Is는 사용자들이 주변의 흡연 구역, 화장실, 쓰레기통 등의 위치 정보를 공유하고 찾을 수 있는 위치 기반 서비스 플랫폼입니다.

## 🏗️ Service Architecture
This project is structured into four main services:
- **Frontend**: React / Next.js  
- **Backend**: Spring Boot (Java)  
- **Database**: MySQL  
- **Infrastructure**: Docker & Docker Compose  

<!-- Diagram Example -->
<!-- ![Architecture Diagram](./docs/architecture.png) -->

## 📂 Directory Structure
```
where_is/
├── api/where_is/          # Spring Boot 백엔드
├── web/where_is/          # React 프론트엔드
├── db/                    # 데이터베이스 관련 파일
├── infra/                 # 인프라 설정
└── doc/                   # 문서
```

## 🔌 Port Mapping
| Service    | Port  |
|------------|-------|
| Frontend   | 3000  |
| Backend    | 8080  |
| Database   | 3306  |

## 📦 Dependencies
| Technology    | Version |
|---------------|---------|
| Spring Boot   | 3.0     |
| Java          | 17.0     |
| React         | 18.0     |
| Next.js       | 1.0     |
| MySQL         | 8.0     |
| Docker/Compose| 1.0     |

## ⚙️ Setup & Run

### 1. Clone Repository
```bash
git clone https://github.com/username/project-name.git
cd project-name
```

### 2. Backend Setup
```bash
cd api/where_is
./gradlew bootRun
```

### 3. Frontend Setup
```bash
cd web/where_is
npm install
npm run dev
```

### 4. Database
```bash
cd db
docker-compose up -d
```

### 5. Access
- Frontend: [http://localhost:3000](http://localhost:3000)  
- Backend: [http://localhost:8080](http://localhost:8080) 
- swagger: [http://localhost:8080/swagger-ui.html] (http://localhost:8080/swagger-ui.html)
