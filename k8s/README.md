# 컨테이너 / 쿠버네티스 실행 가이드

공통컴포넌트(egovframe-template-common-components 5.1.0)를 Docker 및 Kubernetes 환경에서
빌드하고 실행하는 방법을 정리한다.

| 항목 | 값 |
|---|---|
| 빌드 이미지 | `maven:3.9-eclipse-temurin-17` (JDK 17) |
| 런타임 이미지 | `tomcat:11.0-jre17` (Tomcat 11.0, Temurin JRE 17) |
| 이미지 태그 | `egovframe-template-common-components:5.1.0` |
| 컨텍스트 경로 / 포트 | `/` (ROOT) / 8080 |
| 실행 계정 | UID/GID 1000 (non-root) |
| 첨부파일 경로 | `/upload` (`Globals.fileStorePath`, `Globals.SynchrnServerPath`) |

## 사전 준비

- Docker 20.10 이상 (BuildKit 사용)
- Kubernetes 클러스터 및 `kubectl` (k8s 배포 시)
- Maven, JDK 17 (호스트에서 암호화 키 초기화 도구를 실행할 때)

이미지를 빌드하기 **전에** 호스트의 프로젝트 루트에서 아래 두 가지를 마친다. 두 설정 모두
war 안에 들어가므로, 바꾼 뒤에는 이미지를 다시 빌드해야 반영된다.

1. **DB 접속 정보** — `src/main/resources/egovframework/egovProps/globals.properties` 의
   `Globals.DbType`, `Globals.<db>.Url` / `UserName` / `Password` 를 컨테이너에서 접근할 수 있는
   주소로 맞춘다. 기본값 `127.0.0.1` 은 컨테이너 자신을 가리키므로 그대로는 DB 에 연결되지 않는다.
   (호스트에서 띄운 DB 는 Docker Desktop 기준 `host.docker.internal`, k8s 는 DB 서비스의 DNS 이름)
   **DB 는 기동 시점에 필요하다.** 연결되지 않으면 스프링 컨텍스트 초기화가 실패해(배치 스케줄러가
   기동 중 DB 를 조회) 정적 리소스를 포함한 모든 경로가 404 를 응답한다.
2. **암호화 키 초기화** — `./init-crypto-key.sh` (Windows: `init-crypto-key.bat`) 를 한 번 실행한다.
   배포 기본 키 그대로 빌드하면 컨테이너는 기동하지만 **정적 리소스를 포함한 모든 요청에
   "암호화 키 초기화가 필요합니다" 안내 페이지(HTTP 503)** 를 응답한다. 자세한 내용은 루트
   [README.md](../README.md) 의 보안 주의사항을 참조한다.

별도의 `mvn package` 선행 실행은 필요하지 않다. 멀티 스테이지 Dockerfile 의 빌드 스테이지에서
war 를 빌드해 풀어 두고, 런타임 스테이지는 풀린 폴더를 `webapps/ROOT` 로 복사한다.
빌드 컨텍스트에는 `pom.xml` 과 `src/` 만 들어간다(`.dockerignore`).

> 참고: M-Gov SMS API(`smeapi`) 는 중앙 저장소에 없는 3rd party 라이브러리로,
> 리포지토리에 동봉된 jar(`src/main/webapp/WEB-INF/lib/project/...`)를 빌드
> 스테이지의 로컬 Maven 저장소에 설치한 뒤 컴파일한다. 이 과정은 Dockerfile 에
> 포함되어 있어 별도로 처리할 필요가 없다.

## Docker 단독 실행

```bash
# 1) 이미지 빌드
docker build -t egovframe-template-common-components:5.1.0 .

# 2) 컨테이너 실행 (호스트 8080 → 컨테이너 8080, 첨부파일은 upload 볼륨에 보존)
docker run --rm -p 8080:8080 --name egovframe-template-common-components \
  -v egov-upload:/upload \
  egovframe-template-common-components:5.1.0
```

## docker compose 실행

```bash
# 빌드 후 백그라운드 기동
docker compose up -d --build

# 로그 확인
docker compose logs -f app

# 종료 (첨부파일 볼륨까지 지우려면 -v 추가)
docker compose down
```

`APP_VERSION` 환경변수로 이미지 태그를 바꿀 수 있다. (기본값 `5.1.0`)

```bash
APP_VERSION=5.1.1 docker compose up -d --build
```

`docker-compose.yml` 은 애플리케이션 컨테이너와 첨부파일 볼륨(`upload`)만 정의한다. DB 는 포함하지 않는다.

## 접속 방법

기동 후 브라우저에서 다음 주소로 접속한다.

```
http://localhost:8080/
```

애플리케이션은 톰캣 ROOT 컨텍스트(`/`)로 배포되며, 메인 화면은
`/index.do`로 포워딩된다. 정상 기동 여부를 빠르게 확인하려면 로그인 없이
응답하는 정적 리소스로 확인할 수 있다.

```bash
curl -I http://localhost:8080/css/egovframework/com/com12.css
# HTTP/1.1 200 : 기동 정상
# HTTP/1.1 503 : 암호화 키가 배포 기본값 → init-crypto-key 실행 후 이미지를 다시 빌드
# HTTP/1.1 404 : 애플리케이션 기동 실패 (대개 DB 연결 실패) → docker logs 로 원인 확인
```

> 로컬/사파리 브라우저에서 HTTPS가 아닌 환경으로 접속할 경우
> `src/main/webapp/WEB-INF/web.xml`의 세션 쿠키 `secure` 설정을 `false`로
> 조정해야 로그인 세션이 정상 동작한다. (운영 환경에서는 `true` 유지 권장)

초기 계정과 비밀번호 변경 절차는 루트 [README.md](../README.md) 의 "기본 사용자 계정 비밀번호 변경" 을 참조한다.

## Kubernetes 배포

```bash
# 이미지를 클러스터가 접근 가능한 레지스트리에 푸시한 뒤
# k8s/deployment.yaml의 image 값을 해당 태그로 수정한다.
kubectl apply -f k8s/service.yaml
kubectl apply -f k8s/deployment.yaml

# 상태 확인
kubectl get pods -l app.kubernetes.io/name=egovframe-template-common-components
kubectl rollout status deployment/egovframe-template-common-components

# 클러스터 밖에서 확인 (Service 는 ClusterIP)
kubectl port-forward service/egovframe-template-common-components 8080:8080
```

| 리소스 | 내용 |
|---|---|
| `deployment.yaml` | Deployment `egovframe-template-common-components`, replicas 1, 요청 500m / 1Gi, 제한 2 CPU / 2Gi |
| `service.yaml` | Service `egovframe-template-common-components`, ClusterIP, 8080 → 컨테이너 `http`(8080) |

### 헬스 체크 경로

`readiness`/`liveness`/`startup` 프로브는 정적 리소스
`/css/egovframework/com/com12.css`를 사용한다. 이 경로는 보안 설정의
`permitAllList`에 포함되어 로그인 없이 200을 반환하고 요청마다 DB 를
조회하지 않으므로, 애플리케이션이 기동한 뒤에는 안정적으로 헬스 판정이 가능하다.
ROOT(`/`)나 `/index.do`는 스프링 시큐리티 및 DB 연결 상태에 따라
302/500을 반환할 수 있어 프로브 경로로는 사용하지 않는다.

암호화 키가 배포 기본값인 이미지는 이 경로에도 503을 응답한다
(`EgovCryptoKeyGuardFilter` 가 `/*` 전체에 적용). 이 경우 `startupProbe` 가 계속 실패해
파드가 Ready 가 되지 않고 재시작을 반복하므로, 키를 초기화한 이미지로 다시 배포한다.
기동 시점에 DB 에 연결하지 못한 경우에도 애플리케이션이 배포되지 않아 404 로 같은 증상이 나타난다.

기동이 느린 환경에서는 `startupProbe`가 첫 검사(30초) 이후 최대 5분까지 기동을 기다리며,
이 기간 동안 `livenessProbe`는 동작하지 않아 불필요한 재시작
(CrashLoopBackOff)을 방지한다.

### 실행 계정과 볼륨

파드는 `runAsNonRoot` / UID·GID·fsGroup 1000 으로 실행하며, 이미지도 같은 계정(`USER 1000:1000`)으로
만들어져 있다. 쓰기가 필요한 경로는 볼륨으로 마운트한다.

| 볼륨 | 마운트 경로 | 용도 |
|---|---|---|
| `tomcat-work` | `/usr/local/tomcat/work` | JSP 컴파일 결과 |
| `tomcat-temp` | `/usr/local/tomcat/temp` | 임시 파일 |
| `tomcat-logs` | `/usr/local/tomcat/logs` | 톰캣 로그 (애플리케이션 로그는 콘솔 출력) |
| `upload` | `/upload` | 첨부파일 (`Globals.fileStorePath`, `Globals.SynchrnServerPath`) |

모두 `emptyDir` 이라 파드가 재생성되면 비워진다. 첨부파일을 보존하거나 `replicas` 를 늘리려면
`upload` 를 PersistentVolumeClaim(다중 파드는 ReadWriteMany)으로 바꾼다. 또한 동시 접속 세션 제한 등
세션은 파드 메모리에 있으므로, 다중 파드로 운영하려면 세션 클러스터링 또는 고정 세션(sticky session)을 함께 구성한다.

### 데이터베이스 연동

본 매니페스트는 애플리케이션 컨테이너만 정의한다. 실제 서비스 동작에는
별도의 DB가 필요하며, 파드가 기동하기 전에 DB 가 먼저 준비되어 있어야 한다. DataSource 접속 정보는 `globals.properties` 에서 읽으며
환경변수로 덮어쓰는 설정은 없으므로, 이미지 빌드 전에 맞춘다(위 "사전 준비").
DB 스키마와 초기 데이터는 프로젝트 루트 `script/` 의 DDL · DML · Comment 로 만든다
(mysql, oracle, altibase, tibero, cubrid, maria, postgres, goldilocks).
