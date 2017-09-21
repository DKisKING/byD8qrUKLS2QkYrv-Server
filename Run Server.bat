@echo off
title Vencillio Server
color B
echo Vencillio is starting up on VPS...
java -cp bin;lib/*; com.vencillio.Server
pause