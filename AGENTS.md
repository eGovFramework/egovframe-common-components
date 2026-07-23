# AGENTS.md

AI 코딩 에이전트(Claude Code, GitHub Copilot, Cursor 등)가 이 저장소에서 작업할 때 참조하는 컨텍스트 문서입니다.
사람 기여자를 위한 안내는 [README.md](README.md)와 [전자정부 표준프레임워크 포털](https://www.egovframe.go.kr)을 참고하세요.

## 프로젝트 개요

- 전자정부 표준프레임워크 **공통컴포넌트**: 전자정부 사업에서 재사용할 수 있도록 개발한 응용SW 컴포넌트 253종의 모음입니다.
- 표준프레임워크 실행환경(egovframe-rte) 기반의 **Spring MVC + MyBatis** 웹 애플리케이션이며, JSP/jQuery 기반 화면을 포함합니다.
- Maven 멀티모듈이 아닌 단일 WAR 프로젝트이며, 버전 등 공통 설정은 부모 POM(`egovframe-web-config-parent`)에서 상속합니다.

## 기술 스택 및 요구 사항

- **JDK 17** (CI 기준: temurin 17), Maven 3.x
- Spring Framework(spring-webmvc), 표준프레임워크 실행환경(egovframe-rte-*), MyBatis 3.5.x, Jakarta Servlet
- 로깅: **SLF4J + Log4j2** (`LoggerFactory.getLogger(...)`)

## 빌드·테스트·실행

```bash
mvn -B verify          # CI(GitHub Actions)와 동일한 빌드+테스트 명령
mvn test               # 단위 테스트만 실행 (src/test/java, 200개 이상)
```

- 웹 실행은 eGovFrame IDE(Eclipse)에서 `Run on Server`를 사용합니다. 자세한 절차는 README의 "공통컴포넌트 구동 방법" 참조.
- DB 접속·인증 방식 등 공통 설정: `src/main/resources/egovframework/egovProps/globals.properties`
- PR을 올리기 전에 반드시 `mvn -B verify`가 통과해야 합니다.

## 디렉터리 구조

```
src/main/java/egovframework/com/
  cmm/   공통 기반(설정, 필터, 인터셉터, 유틸, 공통 VO)
  cop/   협업(게시판, 커뮤니티, 블로그 등)
  dam/   지식관리
  ext/   외부 연계(OAuth, 검색 등)
  sec/   보안/권한(RBAC)
  ssi/   시스템 연계
  sts/   통계
  sym/   시스템 관리(메뉴, 코드, 배치, 로그)
  uat/   사용자 인증(로그인, SSO)
  uss/   사용자 지원(설문, 민원, 알림, 용어사전 등)
  utl/   유틸리티(포맷, 암호화, 파일, 캘린더 등)
src/main/resources/egovframework/
  mapper/  MyBatis 매퍼 XML (DB별 변형 파일)
  spring/  스프링 설정
  message/ 다국어 메시지
src/main/webapp/  JSP·정적 자원 (WEB-INF 포함)
src/test/java/    JUnit 테스트
```

## 계층 구조와 네이밍 규칙

한 기능(컴포넌트)은 다음 계층을 따릅니다. 새 코드도 동일한 구조를 사용하세요.

```
web/EgovXxxController.java            @Controller, 화면 요청 처리
service/EgovXxxService.java           서비스 인터페이스
service/XxxVO.java, Xxx.java          VO/모델 (XxxVO는 검색조건 포함 확장형)
service/impl/EgovXxxServiceImpl.java  @Service 구현체 (EgovAbstractServiceImpl 상속)
service/impl/XxxDAO.java              @Repository, EgovAbstractMapper 상속
```

- 클래스 접두어 `Egov`, 매퍼 XML은 `Egov<기능>_SQL_<db>.xml` 형식입니다.
- **매퍼 XML을 수정할 때는 모든 DB 변형 파일(altibase, cubrid, goldilocks, maria, mysql, oracle, postgres, tibero)을 함께 수정**해야 합니다. 하나만 고치면 다른 DB에서 동작이 갈라집니다.

## 코드 컨벤션

- 클래스 상단 Javadoc에 **개정이력(Modification Information) 표**를 유지합니다. 기존 이력은 수정하지 말고, 의미 있는 변경 시 행을 추가합니다.

```java
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.01.13  홍길동          최초 생성
```

- 디버그 출력은 `System.out.println` 대신 **SLF4J LOGGER** 사용: `LOGGER.debug("... {}", value);`
- 지역 변수 문자열 버퍼는 `StringBuffer`가 아닌 **`StringBuilder`** 사용. static 필드로 가변 버퍼를 공유하지 않습니다(동시성 문제).
- 대소문자 무시 비교는 `toLowerCase().equals()` 대신 **`equalsIgnoreCase()`**, 상수 리터럴을 앞에 둡니다.
- 들여쓰기는 탭(tab)을 사용합니다. 기존 파일의 포맷(줄바꿈·공백)을 불필요하게 바꾸지 마세요. 파일 인코딩은 UTF-8입니다.
- null/입력 검증, CRLF 제거(`EgovWebUtil.removeCRLF`) 등 기존 보안 처리 패턴을 유지합니다.

## 기여 규칙

- 기준 브랜치는 `main`입니다. 포크 후 기능 단위 브랜치에서 작업합니다.
- 커밋·PR 제목은 `fix:`, `refactor:`, `chore:`, `docs:` 등 관례적 접두어 + 한국어 요약을 사용합니다.
- PR은 **하나의 관심사만** 담고, `.github/pull_request_template.md` 항목(수정 사유, 수정 내용, 테스트 여부)을 채웁니다.
- 이미 열려 있는 PR이 수정 중인 파일은 피해서 작업하세요(충돌·중복 리뷰 방지).
- 실제로 수행하지 않은 테스트 항목에 체크하지 않습니다.

## 에이전트가 하지 말아야 할 것

- 컴포넌트 고유 명칭(기능명·화면명·테이블명)과 이미지 경로를 임의로 "교정"하지 않기
- 라이선스 헤더, 개정이력, 부모 POM에서 상속되는 버전 값을 임의 변경하지 않기
- 대규모 일괄 리포맷(수백 줄 diff) PR 금지 — 리뷰 불가능한 변경은 반려됩니다
