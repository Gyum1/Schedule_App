# Schedule 프로젝트 (일정 관리 프로그램)

## 📋 프로젝트 개요
간단한 일정 관리 REST API입니다.  
JDBC와 MySQL을 사용하여 CRUD 기능을 직접 구현하였습니다.

- Java 17
- Spring Boot 3.4.5
- MySQL
- JDBC Template
- 3 Layer Architecture (Controller - Service - Repository)

---

## 📑 ERD

```sql
Table schedules {
  id bigint [pk, auto_increment]
  title varchar(255) [not null]
  author varchar(100) [not null]
  password varchar(100) [not null]
  created_at datetime [not null, default: CURRENT_TIMESTAMP]
  updated_at datetime [not null, default: CURRENT_TIMESTAMP, note: 'ON UPDATE CURRENT_TIMESTAMP']
}
```

📮 API 명세서
1. 일정 생성
- POST /schedules
```bash



{
  "title": "스터디 준비",
  "author": "태겸",
  "password": "1234"
}
```
Response
```bash


일정이 성공적으로 등록되었습니다.
```

2. 전체 일정 조회
- GET /schedules
```bash

[
  {
    "id": 1,
    "title": "스터디 준비",
    "author": "태겸",
    "createdAt": "2025-05-13T19:10:00",
    "updatedAt": "2025-05-13T19:10:00"
  }
]
```
3. 선택한 일정 조회
- GET /schedules/{id}
```bash
{
  "id": 1,
  "title": "스터디 준비",
  "author": "태겸",
  "createdAt": "2025-05-13T19:10:00",
  "updatedAt": "2025-05-13T19:10:00"
}
```

4. 일정 수정
- PUT /schedules/{id}
```bash
{
  "title": "스터디 복습",
  "author": "태겸",
  "password": "1234"
}
```
Response
```bash
일정이 성공적으로 수정되었습니다.
```

5. 일정 삭제
- DELETE /schedules/{id}
```bash
{
  "password": "1234"
}
```
Response
```bash
일정이 성공적으로 삭제되었습니다.
```

❗ 예외 처리
```bash
비밀번호 불일치 → 400 Bad Request + "비밀번호가 일치하지 않습니다."

없는 ID 조회 → 400 Bad Request + "해당 ID의 일정이 존재하지 않습니다."
```

📝 개발 규칙
일정 작성, 조회 시 비밀번호는 절대 반환하지 않음

3 Layer Architecture 적용

Controller : 요청 처리

Service : 비즈니스 로직

Repository : DB 접근

