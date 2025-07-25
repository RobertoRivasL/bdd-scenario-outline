@echo off
chcp 65001 >nul
echo 🧪 Calculadora - Scenario Outline (Paths Seguros)
echo ==================================================

:: Crear directorios de reportes
echo 📁 Creando directorios de reportes...
if exist target\cucumber-reports rmdir /s /q target\cucumber-reports 2>nul
mkdir target\cucumber-reports\html 2>nul
mkdir target\cucumber-reports\json 2>nul
mkdir target\cucumber-reports\junit 2>nul
echo ✅ Directorios creados

:: Determinar qué pruebas ejecutar
set TEST_TYPE=%1
if "%TEST_TYPE%"=="" set TEST_TYPE=all

if "%TEST_TYPE%"=="basicas" (
    echo 🔍 Ejecutando operaciones básicas...
    mvn test -Dcucumber.filter.tags="@operaciones-basicas" -Dcucumber.plugin="pretty,html:target/cucumber-reports/html,json:target/cucumber-reports/json/cucumber-basic.json"
) else if "%TEST_TYPE%"=="especiales" (
    echo 🔍 Ejecutando casos especiales...
    mvn test -Dcucumber.filter.tags="@casos-especiales" -Dcucumber.plugin="pretty,html:target/cucumber-reports/html,json:target/cucumber-reports/json/cucumber-special.json"
) else if "%TEST_TYPE%"=="headless" (
    echo 🔍 Ejecutando en modo headless...
    mvn test -Dwebdriver.headless=true -Dcucumber.plugin="pretty,html:target/cucumber-reports/html,json:target/cucumber-reports/json/cucumber-headless.json"
) else if "%TEST_TYPE%"=="debug" (
    echo 🔍 Ejecutando con debug (navegador visible)...
    mvn test -Dwebdriver.headless=false -Dcucumber.plugin="pretty,html:target/cucumber-reports/html,json:target/cucumber-reports/json/cucumber-debug.json"
) else (
    echo 🔍 Ejecutando todas las pruebas...
    mvn test -Dcucumber.plugin="pretty,html:target/cucumber-reports/html,json:target/cucumber-reports/json/cucumber-all.json"
)

set EXIT_CODE=%ERRORLEVEL%

echo.
if %EXIT_CODE%==0 (
    echo ✅ Pruebas completadas exitosamente
    echo 📊 Reportes disponibles en:
    echo    - HTML: target\cucumber-reports\html\index.html
    echo    - JSON: target\cucumber-reports\json\
    echo    - JUnit: target\cucumber-reports\junit\
    
    :: Abrir reporte HTML automáticamente
    if exist target\cucumber-reports\html\index.html (
        echo 📊 Abriendo reporte...
        start target\cucumber-reports\html\index.html
    )
) else (
    echo ❌ Las pruebas fallaron (código: %EXIT_CODE%)
    echo 🔍 Revisa los logs arriba para más detalles
)

echo.
echo 🔧 Comandos disponibles:
echo    run-tests-safe.bat basicas    # Solo operaciones básicas
echo    run-tests-safe.bat especiales # Solo casos especiales
echo    run-tests-safe.bat headless   # Modo headless
echo    run-tests-safe.bat debug      # Con navegador visible
echo    run-tests-safe.bat            # Todas las pruebas

pause
exit /b %EXIT_CODE%
