# 표준프레임워크 공통컴포넌트

[![eGovFrame](https://img.shields.io/badge/eGovFrame-5.0.7-134F8C?labelColor=white&logo=data:image/svg%2Bxml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAxNzMuMjgyIDE3My4yODIiPjxwYXRoIGZpbGw9IiNmZmYiIGQ9Ik0xNzMuMjgyIDg2LjY1YzAgNDcuODQ0LTM4Ljc5NCA4Ni42MzItODYuNjQ2IDg2LjYzMkMzOC43OTkgMTczLjI4MiAwIDEzNC40OTQgMCA4Ni42NSAwIDM4Ljc4OCAzOC43OTkgMCA4Ni42MzYgMGM0Ny44NTIgMCA4Ni42NDYgMzguNzg4IDg2LjY0NiA4Ni42NSIvPjxwYXRoIGZpbGw9IiMwMDM3NjQiIGQ9Ik0xMjcuMzg5IDgwLjU5OGMtMTMuNzkxLTkuMzY1LTMxLjQzOS01LjU0Mi00MC43MTcgOC41MzMtNy43MiAxMS43NjctMTkuNDA1IDEzLjIzNS0yMy45MDYgMTMuMjM1LTE0Ljc1MSAwLTI0LjgyNC0xMC4zNjctMjcuODE4LTIxLjA5NWgtLjAxYy0uMDM5LS4xMDgtLjA1OS0uMi0uMDktLjMwNy0uMDI1LS4xMi0uMDU2LS4yMzMtLjA4OS0uMzY2LTEuMTc3LTQuNDY3LTEuNDY3LTYuNjA5LTEuNDY3LTExLjM2OCAwLTI1LjY1IDI2LjMyMS01NC4yMTMgNjQuMjE1LTU0LjIxMyAzOC44MjkgMCA2MS4wNSAyOS41NDUgNjYuNzggNDUuOTc5LS4xMDctLjI5NS0uMjA5LS41ODEtLjI5MS0uODc3LTExLjAxNS0zMi4xMjUtNDEuNDc0LTU1LjIxNi03Ny4zNTctNTUuMjE2LTQ1LjEzIDAtODEuNzI5IDM2LjU5LTgxLjcyOSA4MS43MzkgMCA0MC4zNTEgMjkuMTA4IDc0Ljg5MSA2OS40NzkgNzQuODkxIDMyLjE5NyAwIDUzLjg0Mi0xOC4wNTIgNjMuNzU3LTQyLjkzIDUuNDUtMTMuNjE0IDEuNTk1LTI5LjYwNS0xMC43NTctMzguMDA1Ii8%2BPHBhdGggZmlsbD0iI2U0MDMyZSIgZD0iTTE2NC43ODggNjIuNTg5Yy00Ljc3Ny0xNi4wMjYtMjcuMTUzLTQ3LjU3MS02Ny4yODItNDcuNTcxLTM3Ljg5NCAwLTY0LjIxNCAyOC41NjMtNjQuMjE0IDU0LjIxMiAwIDQuNzU5LjI5IDYuOTAxIDEuNDY2IDExLjM2OC0uNDg5LTEuOTUxLS43NC0zLjkwOC0uNzQtNS44MjMgMC0yNi43MjEgMjYuNzQxLTQ1LjIyNyA1NC4yNDgtNDUuMjI3IDM3LjIxOCAwIDY3LjM4OCAzMC4xNzQgNjcuMzg4IDY3LjM5IDAgMjkuMTczLTE2Ljc4NSA1NC40MzctNDEuMTc5IDY2LjU2NXYuMDIzYzMxLjQ1NS0xMS4zOTEgNTMuOTA4LTQxLjUxMiA1My45MDgtNzYuODg0IDAtOC4zNzgtMS4xMjctMTUuNzU5LTMuNTk1LTI0LjA1MyIvPjwvc3ZnPg%3D%3D)](https://www.egovframe.go.kr)
[![Java](https://img.shields.io/badge/Java-17-007396?logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Framework](https://img.shields.io/badge/Spring%20Framework-6.2.11-%236DB33F?logo=spring&logoColor=white)](https://spring.io/projects/spring-framework)
[![maven](https://img.shields.io/badge/Maven-C71A36?logo=apache-maven&logoColor=white)](https://maven.apache.org/)
[![javascript](https://img.shields.io/badge/javascript-F7DF1E?logo=javascript&logoColor=black)](https://developer.mozilla.org/docs/Web/JavaScript)
[![jQuery](https://img.shields.io/badge/jquery-%230769AD.svg?logo=jquery&logoColor=white)](https://jquery.com/)
![workflow](https://github.com/eGovFramework/egovframe-common-components/actions/workflows/maven.yml/badge.svg)

## 공통컴포넌트 정의

- 공통컴포넌트는 전자정부 사업에서 응용SW 개발 시 공통적으로 활용하기 위하여, 재사용이 가능하도록 개발한 어플리케이션의 집합임
- 공통컴포넌트는 표준프레임워크 실행환경을 기반으로 MVC 아키텍처를 준수하여 설계 및 개발함
- 전자정부 사업에서 쉽게 커스터마이징하여 재사용할 수 있도록 [전자정부 표준프레임워크 포털](https://www.egovframe.go.kr)을 통해 소스코드와 가이드를 제공
<img width="1279" alt="공통컴포넌트 구성도" src="https://user-images.githubusercontent.com/51683963/230310015-5a623206-7218-4401-8598-a3f3b0cedc8a.png">

## 공통컴포넌트 구성

```
egovframe-common-components
  ├─script
  └─src
     ├─main
     │  ├─java/egovframework/com
     │  │  ├─cmm
     │  │  ├─cop
     │  │  ├─dam
     │  │  ├─ext
     │  │  ├─sec
     │  │  ├─ssi/syi
     │  │  ├─sts
     │  │  ├─sym
     │  │  ├─uat
     │  │  ├─uss
     │  │  └─utl
     │  ├─resources
     │  └─webapp
     └─script
```

### 공통컴포넌트 구성 설명

- `script` : 공통컴포넌트에서 지원하는 데이터베이스(mysql, oracle, altibase, tibero, cubrid, mariadb, postgres, goldilocks 8종)에 대한 전체 DDL, DML, Comment 제공
- `src/main/java/egovframework/com/cmm` : 공통으로 사용하는 클래스들로 구성
- `src/main/java/egovframework/com/cop` : 게시판, 커뮤니티, 일정관리 같은 협업 업무에서 사용하는 클래스들로 구성
- `src/main/java/egovframework/com/dam` : 개인지식관리, 지식맵 관리 같은 디지털 자산관리 업무에서 사용하는 클래스들로 구성
- `src/main/java/egovframework/com/ext` : LDAP, Oauth 연동 같은 외부 추가 컴포넌트 클래스들로 구성
- `src/main/java/egovframework/com/sec` : 권한관리, 그룹관리, 롤관리와 같은 보안 업무에서 사용하는 클래스들로 구성
- `src/main/java/egovframework/com/ssi/syi` : 시스템연계, 연계현황관리 서비스 연계 업무에서 사용하는 클래스들로 구성
- `src/main/java/egovframework/com/sts` : 게시물통계, 사용자통계 같은 통계관리 업무에서 사용하는 클래스들로 구성
- `src/main/java/egovframework/com/sym` : 공통코드관리, 로그관리, 메뉴관리 같은 시스템관리 업무에서 사용하는 클래스들로 구성
- `src/main/java/egovframework/com/uat` : 로그인, 인증서관리 같은 통합인증 업무에서 사용하는 클래스들로 구성
- `src/main/java/egovframework/com/uss` : 회원관리, 약관관리, 정보제공/알림 같은 사용자 지원 업무에서 사용하는 클래스들로 구성
- `src/main/java/egovframework/com/utl` : 달력, 포맷/계산/변환, 유효성검증 같은 유틸리티 클래스들로 구성
- `src/main/resources` : 공통컴포넌트 코드에서 사용하는 리소스 폴더
- `src/main/webapp` : 공통컴포넌트 웹페이지 루트 폴더
- `src/script` : 공통컴포넌트에서 지원하는 데이터베이스에 대한 업무 분류별 DDL, DML 제공

## 공통컴포넌트 구동 방법

1. 개발환경 Eclipse IDE를 실행함
2. Eclipse IDE 메뉴에서 File>Import… 를 클릭하여 프로젝트를 가져옴
3. 프로젝트명을 마우스 우클릭하여 Maven > Update Project… > Force Update of Snapshots/Releases 체크 후 Update를 실행함
4. 공통컴포넌트를 설치한 프로젝트 내에 위치한 `globals.properties`(src/main/resources/egovframework/egovProps/globals.properties) 파일의 데이터베이스 정보를 설정함<img width="912" alt="데이터베이스 설정 화면 - globals.properties 파일" src="https://user-images.githubusercontent.com/51683963/230331068-72ac3ab3-df28-4ac2-a3b5-d1d2e58c6b6b.png">
5. **프로젝트 루트에서 암호화 키 초기화 도구를 실행함** — 기본 키 그대로는 어떤 화면으로 접속해도 안내 페이지만 보입니다(아래 [보안 주의사항 1](#1-crypto-암호화-키-변경-algorithmkey--algorithmkeyhash) 참조)
   - Windows: `init-crypto-key.bat` · Linux/macOS·Git Bash: `./init-crypto-key.sh`
6. `globals.properties` 파일의 인증/권한방식 정보를 설정함<img width="600" alt="인증 및 권한방식 설정 화면 - globals.properties 파일" src="https://user-images.githubusercontent.com/51683963/230331630-dd9dc884-0b83-4019-bb81-b162b729d008.png">
7. 프로젝트명을 마우스 우클릭하여 Run As > Run on Server를 실행함
8. 브라우저를 통해 공통컴포넌트 서비스를 확인함

<img width="600" alt="공통컴포넌트 서비스 실행 화면" src="https://github.com/user-attachments/assets/aad11f93-b9d2-4bcf-bf45-c06365899a5d">

## ⚠️ 보안 주의사항 (개발 적용 전 필수 변경)

공통컴포넌트가 기본으로 제공하는 **암호화 키**와 **사용자 계정 정보**는 공개된 값입니다. 개발 환경에 적용하기 전 아래 두 가지를 반드시 변경하시기 바랍니다.

### 1. Crypto 암호화 키 변경 (`algorithmKey` / `algorithmKeyHash`)

표준프레임워크 Crypto 암호화 서비스는 **국정원 ARIA 암호화 알고리즘** 기반입니다. `egov-crypto-config.properties` 의 `algorithmKey` 기본값 `egovframe` 은 공개된 값이라, 그대로 쓰면 이 키로 암호화한 DB 비밀번호 등을 누구나 풀 수 있습니다. 변경하지 않는 것은 **우리집 도어락 비밀번호를 `1234` 초기값 그대로 사용하는 것과 동일**합니다.

**기본 키 그대로는 서비스되지 않습니다.** 애플리케이션은 기동하지만, 아래 중 하나에 해당하면 키를 초기화하기 전까지 **어떤 주소로 접속해도 "암호화 키 초기화가 필요합니다" 안내 페이지(HTTP 503)** 가 보이고 서버 로그에도 같은 안내가 남습니다. 안내 페이지는 보안상 이유와 조치 방법(키 초기화 도구 실행 → 다시 빌드·재기동)을 브라우저 언어(한국어/영어)로 설명합니다.

- `algorithmKey` 가 기본값(`egovframe`)이다 — 설정 파일 경로가 틀려 실행환경이 기본값으로 대체한 경우 포함
- `algorithmKeyHash` 가 기본 키의 해시다(키만 바꾸고 해시를 그대로 둔 경우)
- `algorithmKey` 와 `algorithmKeyHash` 가 서로 맞지 않는다

#### 키 초기화 도구 (권장)

프로젝트 루트에서 실행합니다(Maven 필요).

```
init-crypto-key.bat          (Windows 명령 프롬프트)
./init-crypto-key.sh         (Linux · macOS · Git Bash)
```

옵션 없이 실행하면 키를 **자동 생성**(권장, 무작위 43자)할지 **직접 입력**할지 묻습니다. 도구는 다음을 한 번에 처리합니다.

1. 새 키를 정하고 그 해시(`algorithmKeyHash`)를 계산합니다 — 해시는 키에서 계산되므로 직접 입력하지 않습니다.
2. `globals.properties` 의 DB 비밀번호(`Globals.*.Password`)를 옛 키로 풀어 **새 키로 다시 암호화**합니다. 키만 바꾸면 이 값들을 풀 수 없어 기동이 실패하므로 함께 바꿔야 합니다.
3. 원본을 프로젝트 루트 `crypto-key-backup/` 에 백업하고, 해당 줄만 바꾼 뒤 다시 읽어 검증합니다(실패하면 원복).

| 옵션 | 의미 |
|---|---|
| (없음) | 자동 생성 / 직접 입력을 묻는다 |
| `--generate` | 묻지 않고 자동 생성 (CI·Docker 빌드용, `-y` 와 함께) |
| `--key <값>` / 환경변수 `EGOV_CRYPTO_KEY` | 묻지 않고 이 키 사용 (명령 기록에 남지 않는 환경변수 권장) |
| `--dry-run` | 바뀔 항목만 보여 주고 파일은 쓰지 않음 |
| `-y` | 마지막 확인 질문 생략 |
| `--help` | 전체 옵션 |

- 직접 입력하는 키는 12자 이상, 영문·숫자·기호(공백·한글·역슬래시 제외), 대문자·소문자·숫자·기호 중 3가지 이상이어야 합니다.
- 입력을 받을 수 없는 환경(CI, `docker build`)에서는 기다리지 않고 바로 실패합니다. `--generate -y` 를 쓰십시오.
- 이미 초기화된 키는 다시 바꾸지 않습니다. **운영 중인 키를 바꾸면**(`--force`) 게시물 본문에 저장된 웹에디터 이미지 주소처럼 옛 키로 암호화된 데이터는 새 키로 풀리지 않으므로, 먼저 데이터 영향을 확인하십시오.
- **초기화한 두 설정 파일(`egov-crypto-config.properties`, `globals.properties`)을 공개 저장소에 커밋하지 마십시오.** 자기 프로젝트 저장소에서 아래처럼 제외하는 것을 권장합니다.

```
# .gitignore (각자의 프로젝트 저장소)
src/main/resources/egovframework/egovProps/conf/egov-crypto-config.properties
```

- 참고 자료: [Crypto 위키 가이드](https://www.egovframe.go.kr/wiki/doku.php?id=egovframework:rte5.0:fdl:crypto)

### 2. 기본 사용자 계정 비밀번호 변경

`script/dml` 의 초기 데이터에는 아래 테스트용 사용자 정보가 포함되어 있습니다. (**ID 대소문자 유의**)

| 구분 | ID | 초기 PW |
| --- | --- | --- |
| 일반사용자 | `USER` | `rhdxhd12` |
| 기업사용자 | `ENTERPRISE` | `rhdxhd12` |
| 업무사용자1 | `TEST1` | `rhdxhd12` |
| 업무사용자2 | `webmaster` | `rhdxhd12` |

> 초기 비밀번호 `rhdxhd12` 는 "공통12" 를 영문 자판으로 입력한 값입니다.

- 위 계정의 **비밀번호를 반드시 변경**하고, 사용하지 않는 계정은 **삭제**한 후 운영하시기 바랍니다.
- 개발시 초기값을 그대로 사용할 경우, 그대로 배포까지 이어져서 누구나 알고 있는 계정정보로 시스템에 접근할 수 있어 심각한 보안 위험이 발생합니다.


## 5.0.7 패치 내용

- 2026년 컨트리뷰션 반영
- 접근 제어 보안 수정 (IDOR : 요청 식별자 조작을 통한 다른 사용자 데이터 접근 차단)
- 기능별 접근권한 정비 및 주요 기능의 소유권 검증 보완

## 참조

1. [공통컴포넌트 위키가이드](https://www.egovframe.go.kr/wiki/doku.php?id=egovframework:com:v5.0:init)
2. [개발환경 다운로드](https://www.egovframe.go.kr/home/sub.do?menuNo=94)
3. [공통컴포넌트 다운로드](https://www.egovframe.go.kr/home/sub.do?menuNo=49)
4. [공통컴포넌트 로그인정보](https://www.egovframe.go.kr/wiki/doku.php?id=egovframework:com:v5.0:init_table)
