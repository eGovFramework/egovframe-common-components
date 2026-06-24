# 컨테이너 / 쿠버네티스 실행 가이드

공통컴포넌트(egovframe-common-components)를 Docker 및 Kubernetes 환경에서
빌드하고 실행하는 방법을 정리한다.

## 사전 준비

- Docker 20.10 이상 (BuildKit 사용)
- Kubernetes 클러스터 및 `kubectl` (k8s 배포 시)

별도의 `mvn package` 선행 실행은 필요하지 않다. 멀티 스테이지 Dockerfile의
빌드 스테이지(`maven:3.9-eclipse-temurin-17`)에서 war 빌드를 함께 수행한다.

> 참고: M-Gov SMS API(`smeapi`) 등 중앙 저장소에 없는 3rd party 라이브러리는
> 리포지토리에 동봉된 jar(`src/main/webapp/WEB-INF/lib/project/...`)를 빌드
> 스테이지의 로컬 Maven 저장소에 설치한 뒤 컴파일한다. 이 과정은 Dockerfile에
> 포함되어 있어 사용자가 별도로 처리할 필요가 없다.

## Docker 단독 실행

```bash
# 1) 이미지 빌드
docker build -t egovframe-common-components:5.0.0 .

# 2) 컨테이너 실행 (호스트 8080 → 컨테이너 8080)
docker run --rm -p 8080:8080 --name egov-common-components \
  egovframe-common-components:5.0.0
```

## docker compose 실행

```bash
# 빌드 후 백그라운드 기동
docker compose up -d --build

# 로그 확인
docker compose logs -f app

# 종료
docker compose down
```

`APP_VERSION` 환경변수로 이미지 태그를 바꿀 수 있다. (기본값 `5.0.0`)

```bash
APP_VERSION=5.0.1 docker compose up -d --build
```

## 접속 방법

기동 후 브라우저에서 다음 주소로 접속한다.

```
http://localhost:8080/
```

애플리케이션은 톰캣 ROOT 컨텍스트(`/`)로 배포되며, 메인 화면은
`/index.do`로 포워딩된다. 정상 기동 여부를 빠르게 확인하려면 인증·DB에
의존하지 않는 정적 리소스로 확인할 수 있다.

```bash
curl -I http://localhost:8080/css/egovframework/com/com12.css
# HTTP/1.1 200 응답이면 컨테이너 기동 정상
```

> 로컬/사파리 브라우저에서 HTTPS가 아닌 환경으로 접속할 경우
> `src/main/webapp/WEB-INF/web.xml`의 세션 쿠키 `secure` 설정을 `false`로
> 조정해야 로그인 세션이 정상 동작한다. (운영 환경에서는 `true` 유지 권장)

## Kubernetes 배포

```bash
# 이미지를 클러스터가 접근 가능한 레지스트리에 푸시한 뒤
# k8s/deployment.yaml의 image 값을 해당 태그로 수정한다.
kubectl apply -f k8s/service.yaml
kubectl apply -f k8s/deployment.yaml

# 상태 확인
kubectl get pods -l app.kubernetes.io/name=egov-common-components
kubectl rollout status deployment/egov-common-components
```

### 헬스 체크 경로

`readiness`/`liveness`/`startup` 프로브는 정적 리소스
`/css/egovframework/com/com12.css`를 사용한다. 이 경로는 보안 설정의
`permitAllList`에 포함되어 인증·DB 없이 톰캣 DefaultServlet이 200을
반환하므로, 애플리케이션 기동 직후에도 안정적으로 헬스 판정이 가능하다.
ROOT(`/`)나 `/index.do`는 스프링 시큐리티 및 DB 연결 상태에 따라 기동
초기에 302/500을 반환할 수 있어 프로브 경로로는 사용하지 않는다.

기동이 느린 환경에서는 `startupProbe`가 최대 5분까지 기동을 기다리며,
이 기간 동안 `livenessProbe`는 동작하지 않아 불필요한 재시작
(CrashLoopBackOff)을 방지한다.

### 데이터베이스 연동

본 매니페스트는 애플리케이션 컨테이너만 정의한다. 실제 서비스 동작에는
별도의 DB가 필요하며, DataSource 접속 정보는 이미지 빌드 전
`globals.properties` 등 설정에 맞추거나 ConfigMap/Secret으로 주입하도록
확장한다.
