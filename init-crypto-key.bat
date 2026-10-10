@echo off
rem eGovFrame common components - crypto key initializer
rem
rem Replaces the default algorithmKey in egov-crypto-config.properties with a new key and
rem re-encrypts the DB passwords in globals.properties. With the default key every page
rem shows a notice instead of the service, so run this once after cloning.
rem
rem Usage: init-crypto-key.bat [options]      Help: init-crypto-key.bat --help
rem   Without options it asks whether to generate the key or let you type one.
rem   In CI or Docker builds where no input is possible, use --generate -y. Output language: --lang ko|en.

setlocal
cd /d "%~dp0"

where mvn >nul 2>nul
if errorlevel 1 (
	echo [ERROR] mvn was not found. Install Maven, add it to PATH and run again. 1>&2
	exit /b 1
)

call mvn -q compile exec:java -Dexec.mainClass=egovframework.com.cmm.crypto.EgovCryptoKeyInitializer -Dexec.cleanupDaemonThreads=false "-Dexec.args=%*"
exit /b %ERRORLEVEL%
