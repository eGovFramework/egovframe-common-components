# 표준프레임워크 공통컴포넌트 4.2.0

![java](https://img.shields.io/badge/java-007396?style=for-the-badge&logo=JAVA&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![javascript](https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
![jQuery](https://img.shields.io/badge/jquery-%230769AD.svg?style=for-the-badge&logo=jquery&logoColor=white)  
![workflow](https://github.com/eGovFramework/egovframe-common-components/actions/workflows/maven.yml/badge.svg)

## 공통컴포넌트 정의

- 공통컴포넌트는 전자정부 사업에서 응용SW 개발 시 공통적으로 활용하기 위하여, 재사용이 가능하도록 개발한 어플리케이션의 집합임
- 공통컴포넌트는 표준프레임워크 실행환경을 기반으로 MVC아키텍처를 준수하여 설계 및 개발함
- 전자정부 사업에서 쉽게 커스트마이징하여 재사용할수 있도록 [전자정부 표준프레임워크 포털](https://www.egovframe.go.kr)을 통해 소스코드와 가이드를 제공
<img width="1279" alt="5" src="https://user-images.githubusercontent.com/51683963/230310015-5a623206-7218-4401-8598-a3f3b0cedc8a.png">

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
- `src/main/java/egovframework/com/ext` : LDAP, Oauth 연동 같은 외부추가 컴포넌트 클래스들로 구성
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

1. 개발환경 Eclipse IDE 를 실행함
2. Eclipse IDE 메뉴에서 File>Import… 를 클릭하여 프로젝트를 가져옴
3. 프로젝트명을 마우스 우클릭하여 Maven > Update Project… > Force Update of Snapshots/Releases 체크 후 Update를 실행함
4. 공통컴포넌트를 설치한 프로젝트 내에 위치한 `globals.properties`(src/main/resources/egovframework/egovProps/globals.properties) 파일의 데이터베이스 정보를 설정함<img width="912" alt="6" src="https://user-images.githubusercontent.com/51683963/230331068-72ac3ab3-df28-4ac2-a3b5-d1d2e58c6b6b.png">
5. `globals.properties` 파일의 인증/권한방식 정보를 설정함<img width="600" alt="7" src="https://user-images.githubusercontent.com/51683963/230331630-dd9dc884-0b83-4019-bb81-b162b729d008.png">
6. 프로젝트명을 마우스 우클릭하여 run as > run on server을 실행함
7. 브라우저를 통해 공통컴포넌트 서비스를 확인함![8](https://user-images.githubusercontent.com/51683963/230332233-d753370b-e824-4e27-8509-a50b773b56e0.jpg)  


## 참조

1. [공통컴포넌트 위키가이드](https://www.egovframe.go.kr/wiki/doku.php?id=egovframework:com:v4.1:init)
2. [개발환경 다운로드](https://www.egovframe.go.kr/home/sub.do?menuNo=94)
3. [공통컴포넌트 다운로드](https://www.egovframe.go.kr/home/sub.do?menuNo=49)
4. [공통컴포넌트 로그인정보](https://www.egovframe.go.kr/wiki/doku.php?id=egovframework:com:v4.1:init_table)
