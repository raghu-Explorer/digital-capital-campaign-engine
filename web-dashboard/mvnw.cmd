@echo off
pushd "%~dp0.."
call mvnw.cmd %*
popd
