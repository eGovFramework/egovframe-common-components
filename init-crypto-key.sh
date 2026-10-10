#!/bin/sh
# 공통컴포넌트 암호화 키 초기화
#
# egov-crypto-config.properties 의 배포 기본 키(algorithmKey)를 새 키로 바꾸고,
# 그 키로 암호화된 globals.properties 의 DB 비밀번호를 다시 암호화한다.
# 기본 키 그대로는 어떤 화면으로 접속해도 안내 페이지만 보이므로, 클론한 뒤 처음 한 번 실행한다.
#
# 사용법: ./init-crypto-key.sh [옵션]      도움말: ./init-crypto-key.sh --help
#   옵션 없이 실행하면 키를 자동 생성할지 직접 입력할지 묻는다.
#   CI·Docker 빌드처럼 입력할 수 없는 곳에서는 --generate -y 를 쓴다. 출력 언어는 --lang ko|en.

cd "$(dirname "$0")" || exit 1

if ! command -v mvn >/dev/null 2>&1; then
	echo "[오류] mvn 명령을 찾을 수 없습니다. Maven 을 설치하고 PATH 에 추가한 뒤 다시 실행하십시오." >&2
	exit 1
fi

exec mvn -q compile exec:java \
	-Dexec.mainClass=egovframework.com.cmm.crypto.EgovCryptoKeyInitializer \
	-Dexec.cleanupDaemonThreads=false \
	"-Dexec.args=$*"
