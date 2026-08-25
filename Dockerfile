# syntax=docker/dockerfile:1.7
# Multi-stage build for egovframe-common-components.
# Stage 1 builds the war with Maven.
# Stage 2 serves it from Tomcat 10.1 on Temurin JRE 17.

# ---------- Build ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /workspace

COPY pom.xml ./
COPY src ./src
COPY script ./script

# M-Gov SMS API(smeapi)는 중앙 저장소에 없는 3rd party jar로, 리포지토리에
# 동봉된 jar(src/main/webapp/WEB-INF/lib/project/...)를 빌드 스테이지의 로컬
# Maven 저장소에 먼저 설치해야 깨끗한 컨테이너 빌드에서 컴파일이 가능하다.
# (로컬 톰캣 빌드에서는 file:// projectRepository로 해결되지만, 격리된 빌드에서는
#  go-offline 단계가 해당 jar를 미리 받지 못해 'package x3.client.smeapi does not exist'
#  컴파일 오류가 발생한다.)
RUN --mount=type=cache,target=/root/.m2 \
    mvn -B -ntp install:install-file \
        -Dfile=src/main/webapp/WEB-INF/lib/project/smeapi/2.7.0/smeapi-2.7.0.jar \
        -DgroupId=project -DartifactId=smeapi -Dversion=2.7.0 -Dpackaging=jar

RUN --mount=type=cache,target=/root/.m2 \
    mvn -B -ntp -DskipTests package && \
    cp target/*.war /workspace/ROOT.war

# ---------- Runtime ----------
FROM tomcat:10.1-jre17

RUN rm -rf /usr/local/tomcat/webapps/ROOT
COPY --from=build /workspace/ROOT.war /usr/local/tomcat/webapps/ROOT.war

ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=70 -XX:+ExitOnOutOfMemoryError"

EXPOSE 8080

CMD ["catalina.sh","run"]
