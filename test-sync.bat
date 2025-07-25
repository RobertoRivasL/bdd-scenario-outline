@echo off
echo 🔄 PRUEBA RÁPIDA - Verificar Sincronización
echo ==========================================

echo 🧪 Ejecutando todas las pruebas...

echo.
echo 1️⃣ Probando operaciones básicas...
mvn test -Dcucumber.filter.tags="@operaciones-basicas" -Dcucumber.plugin="pretty" -q
if %errorlevel%==0 (
    echo ✅ Operaciones básicas: ÉXITO
) else (
    echo ❌ Operaciones básicas: FALLÓ
)

echo.
echo 2️⃣ Probando números decimales...
mvn test -Dcucumber.filter.tags="@numeros-decimales" -Dcucumber.plugin="pretty" -q
if %errorlevel%==0 (
    echo ✅ Números decimales: ÉXITO
) else (
    echo ❌ Números decimales: FALLÓ
)

echo.
echo 3️⃣ Probando casos especiales...
mvn test -Dcucumber.filter.tags="@casos-especiales" -Dcucumber.plugin="pretty" -q
if %errorlevel%==0 (
    echo ✅ Casos especiales: ÉXITO
) else (
    echo ❌ Casos especiales: FALLÓ
)

echo.
echo 4️⃣ Probando funcionalidad limpiar...
mvn test -Dcucumber.filter.tags="@funcionalidad-limpiar" -Dcucumber.plugin="pretty" -q
if %errorlevel%==0 (
    echo ✅ Funcionalidad limpiar: ÉXITO
) else (
    echo ❌ Funcionalidad limpiar: FALLÓ
)

echo.
echo 🎯 Ejecutando TODAS las pruebas juntas...
mvn test -Dcucumber.plugin="pretty" -q
set EXIT_CODE=%errorlevel%

echo.
if %EXIT_CODE%==0 (
    echo 🎉 ¡TODAS LAS PRUEBAS PASARON!
    echo ✅ Sincronización completada exitosamente
    echo.
    echo 📊 Para generar reportes:
    echo    run-with-screenshots.bat reports
    echo.
    echo 👁️ Para ver en modo visual:
    echo    run-with-screenshots.bat visual
) else (
    echo ❌ Algunas pruebas fallaron
    echo 🔍 Ejecuta con más detalles:
    echo    mvn test -Dcucumber.plugin="pretty"
)

pause
exit /b %EXIT_CODE%
