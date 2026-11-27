# syntax=docker/dockerfile:1.7
# Multi-stage build for egovframe-template-common-components (공통컴포넌트 all-in-one).
# Stage 1 builds the war with Maven (JDK 17) and unpacks it.
# Stage 2 serves it from Tomcat 11.0 on Temurin JRE 17 as a non-root user (UID 1000).

# ---------- Build ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /workspace

# 암호화 키: src 의 egov-crypto-config.properties 가 배포 기본 키(egovframe)이면 컨테이너는 기동하지만
# 정적 리소스를 포함한 모든 요청에 "암호화 키 초기화가 필요합니다" 안내 페이지(HTTP 503)를 응답한다.
# 이미지를 빌드하기 전에 호스트의 프로젝트 루트에서 init-crypto-key.sh(Windows: init-crypto-key.bat)를
# 한 번 실행해 두어야 한다.
# 빌드 안에서 --generate 로 자동 생성하지 않는 이유: 빌드할 때마다 키가 바뀌어 기존 암호화 데이터를 풀 수 없게 된다.
COPY pom.xml ./
COPY src ./src

# M-Gov SMS API(smeapi)는 중앙 저장소에 없는 3rd party jar로, 리포지토리에
# 동봉된 jar(src/main/webapp/WEB-INF/lib/project/...)를 빌드 스테이지의 로컬
# Maven 저장소에 먼저 설치해 격리된 컨테이너 빌드에서도 컴파일되게 한다.
RUN --mount=type=cache,target=/root/.m2 \
    mvn -B -ntp install:install-file \
        -Dfile=src/main/webapp/WEB-INF/lib/project/smeapi/2.7.0/smeapi-2.7.0.jar \
        -DgroupId=project -DartifactId=smeapi -Dversion=2.7.0 -Dpackaging=jar

# 테스트는 pom.xml 의 skipTests 기본값(true)으로 생략된다.
# war 는 빌드 스테이지에서 미리 풀어 둔다 - 런타임 계정이 webapps 에 쓰지 않아도 되게 하기 위해서다.
RUN --mount=type=cache,target=/root/.m2 \
    mvn -B -ntp package && \
    mkdir /workspace/ROOT && \
    cd /workspace/ROOT && \
    jar -xf /workspace/target/egovframe-template-common-components-*.war

# ---------- Runtime ----------
# pom.xml(cargo tomcat11x), globals.properties(File Upload Settings for Tomcat 11)와 같은 Tomcat 11 기준.
FROM tomcat:11.0-jre17

# k8s/deployment.yaml 의 securityContext(runAsUser/runAsGroup/fsGroup 1000)와 같은 계정으로 실행한다.
# /upload 는 globals.properties 의 Globals.fileStorePath(/upload/allinone), Globals.SynchrnServerPath(/upload/Synch/) 위치다.
RUN rm -rf /usr/local/tomcat/webapps/ROOT && \
    mkdir -p /upload/allinone /upload/Synch /usr/local/tomcat/conf/Catalina && \
    chown -R 1000:1000 /upload \
        /usr/local/tomcat/conf/Catalina \
        /usr/local/tomcat/logs \
        /usr/local/tomcat/temp \
        /usr/local/tomcat/work \
        /usr/local/tomcat/webapps

COPY --from=build --chown=1000:1000 /workspace/ROOT /usr/local/tomcat/webapps/ROOT

ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=70 -XX:+ExitOnOutOfMemoryError"

USER 1000:1000

EXPOSE 8080

CMD ["catalina.sh","run"]
