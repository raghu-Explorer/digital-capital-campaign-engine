<#
.SYNOPSIS
    VentureSeed Capital Networks - Microservices Orchestration Script
.DESCRIPTION
    Builds and launches all VentureSeed Spring Cloud microservices and the Web Dashboard.
#>

param(
    [switch]$BuildOnly,
    [switch]$SkipBuild,
    [switch]$FrontendOnly
)

$ErrorActionPreference = "Continue"
$RootPath = Split-Path -Parent $MyInvocation.MyCommand.Path

Write-Host "=================================================================" -ForegroundColor Cyan
Write-Host "  🌱 VentureSeed Capital Networks - Microservices Launcher       " -ForegroundColor Green
Write-Host "=================================================================" -ForegroundColor Cyan

if ($FrontendOnly) {
    Write-Host "[Info] Launching Web Dashboard only..." -ForegroundColor Yellow
    Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$RootPath\web-dashboard'; node server.js"
    Start-Sleep -Seconds 2
    Start-Process "http://localhost:3000"
    exit 0
}

# Step 1: Maven Build
if (-not $SkipBuild) {
    Write-Host "`n[Step 1/3] Building all microservices with Maven..." -ForegroundColor Cyan
    $mvnCmd = "$RootPath\mvnw.cmd"
    if (-not (Test-Path $mvnCmd)) {
        $mvnCmd = "mvn"
    }

    & $mvnCmd clean package -DskipTests
    if ($LASTEXITCODE -ne 0) {
        Write-Host "`n[Warning] Build encountered an error. Proceeding with caution or use offline dashboard." -ForegroundColor Yellow
    } else {
        Write-Host "`n[Success] All microservices compiled and packaged into JARs!" -ForegroundColor Green
    }
}

if ($BuildOnly) {
    Write-Host "`n[Done] Build completed." -ForegroundColor Green
    exit 0
}

# Step 2: Start Services in Dedicated Terminals
Write-Host "`n[Step 2/3] Booting Microservices Mesh..." -ForegroundColor Cyan

# 1. Eureka Server (8761)
Write-Host " -> Starting [1/6] Eureka Service Discovery (Port 8761)..." -ForegroundColor Magenta
Start-Process powershell -ArgumentList "-NoExit", "-Command", "$host.UI.RawUI.WindowTitle='Eureka Server :8761'; cd '$RootPath\eureka-server'; java -jar target/eureka-server-1.0.0-SNAPSHOT.jar"

Write-Host "    Waiting 12 seconds for Eureka registry warmup..." -ForegroundColor DarkGray
Start-Sleep -Seconds 12

# 2. Auth Service (8081)
Write-Host " -> Starting [2/6] Auth & Identity Service (Port 8081)..." -ForegroundColor Yellow
Start-Process powershell -ArgumentList "-NoExit", "-Command", "$host.UI.RawUI.WindowTitle='Auth Service :8081'; cd '$RootPath\auth-service'; java -jar target/auth-service-1.0.0-SNAPSHOT.jar"

# 3. Campaign Service (8082)
Write-Host " -> Starting [3/6] Campaign Service (Port 8082)..." -ForegroundColor Yellow
Start-Process powershell -ArgumentList "-NoExit", "-Command", "$host.UI.RawUI.WindowTitle='Campaign Service :8082'; cd '$RootPath\campaign-service'; java -jar target/campaign-service-1.0.0-SNAPSHOT.jar"

# 4. Payment Service (8084)
Write-Host " -> Starting [4/6] Payment Processor Service (Port 8084)..." -ForegroundColor Yellow
Start-Process powershell -ArgumentList "-NoExit", "-Command", "$host.UI.RawUI.WindowTitle='Payment Service :8084'; cd '$RootPath\payment-service'; java -jar target/payment-service-1.0.0-SNAPSHOT.jar"

# 5. Contribution Service (8083)
Write-Host " -> Starting [5/6] Contribution Service (Port 8083)..." -ForegroundColor Yellow
Start-Process powershell -ArgumentList "-NoExit", "-Command", "$host.UI.RawUI.WindowTitle='Contribution Service :8083'; cd '$RootPath\contribution-service'; java -jar target/contribution-service-1.0.0-SNAPSHOT.jar"

Write-Host "    Waiting 10 seconds for service registration..." -ForegroundColor DarkGray
Start-Sleep -Seconds 10

# 6. API Gateway (8080)
Write-Host " -> Starting [6/6] API Gateway Ingress (Port 8080)..." -ForegroundColor Green
Start-Process powershell -ArgumentList "-NoExit", "-Command", "$host.UI.RawUI.WindowTitle='API Gateway :8080'; cd '$RootPath\api-gateway'; java -jar target/api-gateway-1.0.0-SNAPSHOT.jar"

# Step 3: Web Dashboard
Write-Host "`n[Step 3/3] Starting Web Dashboard (Port 3000)..." -ForegroundColor Cyan
Start-Process powershell -ArgumentList "-NoExit", "-Command", "$host.UI.RawUI.WindowTitle='Web Dashboard :3000'; cd '$RootPath\web-dashboard'; node server.js"

Start-Sleep -Seconds 3
Write-Host "`n=================================================================" -ForegroundColor Green
Write-Host "  ✨ All Services Started! Opening http://localhost:3000        " -ForegroundColor Green
Write-Host "=================================================================" -ForegroundColor Green
Start-Process "http://localhost:3000"
