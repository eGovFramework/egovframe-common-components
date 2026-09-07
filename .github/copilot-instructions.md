# GitHub Copilot 지침 — 표준프레임워크 공통컴포넌트

이 저장소는 전자정부 표준프레임워크 **공통컴포넌트**(Spring MVC + MyBatis 기반 단일 WAR)입니다.
상세 컨텍스트는 저장소 루트의 [AGENTS.md](../AGENTS.md)를 참고하세요.

## 빌드·테스트

- JDK 17, Maven. 검증 명령: `mvn -B verify` (CI와 동일)
- 버전 등 공통 설정은 부모 POM(`egovframe-web-config-parent`)에서 상속 — 임의로 버전을 명시하지 않기

## 구조·네이밍 규칙

- 패키지: `egovframework.com.{cmm|cop|dam|ext|sec|ssi|sts|sym|uat|uss|utl}`
- 계층: `web/EgovXxxController` → `service/EgovXxxService`(+ `XxxVO`) → `service/impl/EgovXxxServiceImpl`(`EgovAbstractServiceImpl` 상속) → `service/impl/XxxDAO`(`EgovAbstractMapper` 상속)
- 클래스 접두어 `Egov`, 매퍼 XML은 `Egov<기능>_SQL_<db>.xml`
- **매퍼 XML 수정 시 8개 DB 변형 파일(altibase, cubrid, goldilocks, maria, mysql, oracle, postgres, tibero)을 모두 함께 수정**

## 코드 작성 규칙

- 디버그 출력: `System.out.println` 금지 → SLF4J `LOGGER.debug("... {}", value)`
- 지역 문자열 버퍼: `StringBuffer` 대신 `StringBuilder`, static 가변 버퍼 공유 금지(동시성)
- 대소문자 무시 비교: `toLowerCase().equals()` 대신 `"상수".equalsIgnoreCase(변수)`
- 들여쓰기는 탭(tab), 인코딩 UTF-8. 기존 포맷을 불필요하게 재정렬하지 않기
- 클래스 상단 Javadoc의 개정이력(Modification Information) 표 유지 — 기존 이력 수정 금지, 의미 있는 변경 시 행 추가
- 기존 보안 처리 패턴 유지 (입력 검증, `EgovWebUtil.removeCRLF` 등)

## 하지 말 것

- 라이선스 헤더·개정이력·컴포넌트 고유 명칭 임의 변경
- 대규모 일괄 리포맷(리뷰 불가능한 diff)
- 부모 POM에서 관리되는 의존성 버전 재정의
