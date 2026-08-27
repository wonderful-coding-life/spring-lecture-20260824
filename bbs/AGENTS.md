# AGENTS.md

## 1. 프로젝트 개요

이 프로젝트는 Spring Boot 기반의 웹 게시판 애플리케이션이다.

주요 기술은 다음과 같다.

- Spring Boot
- Spring MVC
- Spring Data JPA
- Spring Security
- Thymeleaf
- MySQL
- Gradle
- Lombok
- Tailwind CSS

Java, Spring Boot, Gradle 및 라이브러리 버전은 기존 프로젝트 설정을 따른다.  
별도의 요청이 없다면 버전이나 의존성을 임의로 변경하지 않는다.

구현할 기능과 완료 조건은 `PRD.md`를 기준으로 한다.

---

## 2. 작업 원칙

- 작업을 시작하기 전에 기존 파일과 코드를 먼저 확인한다.
- 요청과 직접 관련된 파일만 생성하거나 수정한다.
- 기존 코드의 구조와 명명 규칙을 우선하여 따른다.
- 불필요한 클래스, 인터페이스, 계층 또는 추상화를 만들지 않는다.
- 새로운 의존성은 반드시 필요한 경우에만 추가한다.
- 기존 기능을 임의로 삭제하거나 동작을 변경하지 않는다.
- 요구사항이 불명확한 경우 기존 코드와 `PRD.md`의 내용을 우선한다.

---

## 3. 패키지 구조

기존 프로젝트의 기본 패키지 아래에 다음 구조를 사용한다.

```text
entity
repository
service
controller
form
config
```

역할이 명확하지 않은 패키지를 임의로 추가하지 않는다.

---

## 4. 계층 구조

다음 호출 구조를 유지한다.

```text
Controller → Service → Repository
```

- Controller는 요청 처리, Validation 결과 확인, 화면 이동을 담당한다.
- Service는 비즈니스 로직, 권한 검사, 트랜잭션을 담당한다.
- Repository는 데이터 접근을 담당한다.
- Controller에서 Repository를 직접 호출하지 않는다.
- 의존성은 생성자 주입 방식으로 주입한다.

---

## 5. Entity 및 Repository 규칙

- Entity에는 JPA와 Lombok을 사용한다.
- 테이블명과 컬럼명은 명시적으로 지정한다.
- 기본키는 자동 증가 방식을 사용한다.
- 연관관계는 요구사항에 필요한 범위에서만 설정한다.
- Repository는 Spring Data JPA를 사용한다.
- 단순 조회는 Spring Data JPA 메서드 이름 규칙을 우선하여 사용한다.
- 불필요한 커스텀 Repository나 복잡한 쿼리를 만들지 않는다.

---

## 6. Service 규칙

- Service 클래스에는 `@Service`를 사용한다.
- 존재하지 않는 데이터, 중복 데이터, 권한 위반은 Service에서 검사한다.
- 게시글 작성자 권한 검사는 Controller가 아니라 Service에서 처리한다.

---

## 7. Form 및 Validation 규칙

- 사용자 입력값은 Entity가 아니라 Form 객체로 받는다.
- Form 클래스는 `form` 패키지에 작성한다.
- 필수 입력값과 길이, 형식 검사는 Bean Validation 애너테이션을 사용한다.
- Controller에서는 `@Valid`와 `BindingResult`를 사용한다.
- Validation 오류는 Thymeleaf 화면의 해당 입력 항목 아래에 표시한다.
- Form 객체를 Entity로 변환하거나 Entity를 수정하는 작업은 Service에서 처리한다.

---

## 8. Spring Security 규칙

- 비밀번호는 `BCryptPasswordEncoder`로 암호화한다.

---

## 9. Thymeleaf 및 화면 규칙

- Thymeleaf 템플릿은 `src/main/resources/templates` 아래에 작성한다.
- CSS와 JavaScript는 `src/main/resources/static` 아래에 작성한다.
- Thymeleaf 표준 문법을 사용한다.
- 등록과 수정 화면처럼 구조가 같은 화면은 가능한 경우 하나의 템플릿으로 재사용한다.
- 화면은 반응형으로 작성한다.
- CSS는 Tailwind Play CDN을 사용하여 별도의 프런트엔드 빌드 과정을 추가하지 않는다.

---

## 10. 작업 완료 보고

작업이 끝나면 다음 내용을 간단히 정리한다.

1. 생성하거나 수정한 파일
2. 구현한 기능
3. 주요 설계 또는 권한 처리 내용
4. 남아 있는 문제 또는 확인이 필요한 사항

---

## 11. 구현 단계 관리

구현 진행 상태는 프로젝트 루트의 `progress.json`으로 관리한다.

- 세션 시작 시 또는 단계 관련 요청을 받으면 `progress.json`을 먼저 읽어 현재 상태를 파악한다.
- 단계 작업을 시작할 때 해당 항목의 `status`를 `"in_progress"`로 업데이트한다.
- 단계가 완료되면 해당 항목의 `status`를 `"done"`으로 업데이트한다.
- `status` 값은 다음 세 가지만 사용한다.

| 값 | 의미 |
|---|---|
| `"pending"` | 아직 시작하지 않음 |
| `"in_progress"` | 현재 진행 중 |
| `"done"` | 완료 |
