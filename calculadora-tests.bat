@echo off
echo 🎯 CALCULADORA - SCENARIO OUTLINE DEFINITIVO
echo ===========================================

if "%1"=="help" goto :show_help
if "%1"=="" goto :show_help

if "%1"=="basicas" (
    echo 🔍 Ejecutando operaciones básicas...
    mvn test -Dcucumber.filter.tags="@operaciones-basicas" -Dcucumber.plugin="pretty"
) else if "%1"=="decimales" (
    echo 🔍 Ejecutando números decimales...
    mvn test -Dcucumber.filter.tags="@numeros-decimales" -Dcucumber.plugin="pretty"
) else if "%1"=="especiales" (
    echo 🔍 Ejecutando casos especiales...
    mvn test -Dcucumber.filter.tags="@casos-especiales" -Dcucumber.plugin="pretty"
) else if "%1"=="limpiar" (
    echo 🔍 Ejecutando funcionalidad limpiar...
    mvn test -Dcucumber.filter.tags="@funcionalidad-limpiar" -Dcucumber.plugin="pretty"
) else if "%1"=="all" (
    echo 🔍 Ejecutando TODAS las pruebas...
    mvn test -Dcucumber.plugin="pretty"
) else if "%1"=="headless" (
    echo 🔍 Ejecutando en modo headless...
    mvn test -Dwebdriver.headless=true -Dcucumber.plugin="pretty"
) else (
    goto :show_help
)

set EXIT_CODE=%ERRORLEVEL%
echo.
if %EXIT_CODE%==0 (
    echo ✅ Pruebas completadas exitosamente
    echo.
    echo 🎓 Scenario Outline funcionando perfectamente:
    echo    ✅ Múltiples Examples con datos de prueba
    echo    ✅ Tags organizados para ejecución selectiva
    echo    ✅ Step Definitions únicos sin conflictos
    echo    ✅ Manejo de enteros y decimales
    echo    ✅ Validación de errores
) else (
    echo ❌ Algunas pruebas fallaron (código: %EXIT_CODE%)
)

echo.
goto :show_help

:show_help
echo 🔧 Comandos disponibles:
echo    calculadora-tests.bat basicas     # Operaciones básicas (enteros)
echo    calculadora-tests.bat decimales   # Números decimales
echo    calculadora-tests.bat especiales  # Casos especiales y errores
echo    calculadora-tests.bat limpiar     # Funcionalidad limpiar
echo    calculadora-tests.bat all         # Todas las pruebas
echo    calculadora-tests.bat headless    # Modo headless (sin navegador)
echo    calculadora-tests.bat help        # Mostrar esta ayuda
if not "%1"=="help" if not "%1"=="" pause
exit /b %EXIT_CODE%
