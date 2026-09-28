@echo off
title VentureSeed Microservices Launcher
setlocal

echo =================================================================
echo   VentureSeed Capital Networks - Microservices Quick Launcher
echo =================================================================
echo.
echo Choose an option:
echo [1] Start All (Build JARs + Launch Eureka, Gateway, Services, Web UI)
echo [2] Start Web Dashboard Only (Instant Port 3000)
echo [3] Build Only (Compile all microservices with Maven)
echo.

set /p choice="Enter option (1, 2, or 3): "

if "%choice%"=="1" (
    powershell -ExecutionPolicy Bypass -File "%~dp0start-all.ps1"
) else if "%choice%"=="2" (
    powershell -ExecutionPolicy Bypass -File "%~dp0start-all.ps1" -FrontendOnly
) else if "%choice%"=="3" (
    powershell -ExecutionPolicy Bypass -File "%~dp0start-all.ps1" -BuildOnly
) else (
    echo Invalid choice. Starting Web Dashboard by default...
    powershell -ExecutionPolicy Bypass -File "%~dp0start-all.ps1" -FrontendOnly
)

pause
