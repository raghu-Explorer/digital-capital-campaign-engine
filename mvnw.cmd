@REM ----------------------------------------------------------------------------
@REM Maven Wrapper Script for Windows
@REM ----------------------------------------------------------------------------
@echo off
setlocal

set "DIR=%~dp0"
set "MAVEN_VERSION=3.9.9"
set "MAVEN_HOME=%DIR%.tools\apache-maven-%MAVEN_VERSION%"

if exist "%MAVEN_HOME%\bin\mvn.cmd" (
    set "MVN_CMD=%MAVEN_HOME%\bin\mvn.cmd"
    goto runMvn
)

where mvn >nul 2>nul
if %ERRORLEVEL% equ 0 (
    set "MVN_CMD=mvn"
    goto runMvn
)

echo [VentureSeed] Maven not found in PATH. Downloading portable Apache Maven %MAVEN_VERSION%...
if not exist "%DIR%.tools" mkdir "%DIR%.tools"

powershell -Command "[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; Invoke-WebRequest -Uri 'https://archive.apache.org/dist/maven/maven-3/%MAVEN_VERSION%/binaries/apache-maven-%MAVEN_VERSION%-bin.zip' -OutFile '%DIR%.tools\maven.zip' -UseBasicParsing; Expand-Archive -Path '%DIR%.tools\maven.zip' -DestinationPath '%DIR%.tools' -Force; Remove-Item '%DIR%.tools\maven.zip'"

if exist "%MAVEN_HOME%\bin\mvn.cmd" (
    echo [VentureSeed] Apache Maven %MAVEN_VERSION% successfully configured!
    set "MVN_CMD=%MAVEN_HOME%\bin\mvn.cmd"
    goto runMvn
) else (
    echo [Error] Failed to install Maven wrapper. Please ensure Java 21 and internet connectivity are available.
    exit /b 1
)

:runMvn
"%MVN_CMD%" %*
exit /b %ERRORLEVEL%
