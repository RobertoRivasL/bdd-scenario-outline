#!/bin/bash

# Script de ejecución seguro con manejo de directorios
echo "🧪 Calculadora - Scenario Outline (Paths Seguros)"
echo "=================================================="

# Función para crear directorios de reportes
create_report_dirs() {
    echo "📁 Creando directorios de reportes..."
    rm -rf target/cucumber-reports 2>/dev/null || true
    mkdir -p target/cucumber-reports/html
    mkdir -p target/cucumber-reports/json
    mkdir -p target/cucumber-reports/junit
    echo "✅ Directorios creados"
}

# Función para abrir reportes
open_reports() {
    local report_file="target/cucumber-reports/html/index.html"
    if [[ -f "$report_file" ]]; then
        echo "📊 Abriendo reporte..."
        if [[ "$OSTYPE" == "msys" ]] || [[ "$OSTYPE" == "win32" ]]; then
            start "$report_file"
        elif [[ "$OSTYPE" == "darwin"* ]]; then
            open "$report_file"
        else
            xdg-open "$report_file" 2>/dev/null || echo "Reporte disponible en: $report_file"
        fi
    else
        echo "⚠️  Reporte HTML no encontrado en: $report_file"
    fi
}

# Crear directorios antes de ejecutar
create_report_dirs

case "${1:-all}" in
    "basicas"|"basic")
        echo "🔍 Ejecutando operaciones básicas..."
        mvn test -Dcucumber.filter.tags="@operaciones-basicas" -Dcucumber.plugin="pretty,html:target/cucumber-reports/html,json:target/cucumber-reports/json/cucumber-basic.json"
        ;;
    "especiales"|"special")
        echo "🔍 Ejecutando casos especiales..."
        mvn test -Dcucumber.filter.tags="@casos-especiales" -Dcucumber.plugin="pretty,html:target/cucumber-reports/html,json:target/cucumber-reports/json/cucumber-special.json"
        ;;
    "headless")
        echo "🔍 Ejecutando en modo headless..."
        mvn test -Dwebdriver.headless=true -Dcucumber.plugin="pretty,html:target/cucumber-reports/html,json:target/cucumber-reports/json/cucumber-headless.json"
        ;;
    "debug")
        echo "🔍 Ejecutando con debug (sin headless)..."
        mvn test -Dwebdriver.headless=false -Dcucumber.plugin="pretty,html:target/cucumber-reports/html,json:target/cucumber-reports/json/cucumber-debug.json"
        ;;
    "all"|*)
        echo "🔍 Ejecutando todas las pruebas..."
        mvn test -Dcucumber.plugin="pretty,html:target/cucumber-reports/html,json:target/cucumber-reports/json/cucumber-all.json"
        ;;
esac

exit_code=$?

echo ""
if [[ $exit_code -eq 0 ]]; then
    echo "✅ Pruebas completadas exitosamente"
    echo "📊 Reportes disponibles en:"
    echo "   - HTML: target/cucumber-reports/html/index.html"
    echo "   - JSON: target/cucumber-reports/json/"
    echo "   - JUnit: target/cucumber-reports/junit/"
    
    # Intentar abrir reporte automáticamente
    open_reports
else
    echo "❌ Las pruebas fallaron (código: $exit_code)"
    echo "🔍 Revisa los logs arriba para más detalles"
fi

echo ""
echo "🔧 Comandos disponibles:"
echo "   ./run-tests-safe.sh basicas    # Solo operaciones básicas"
echo "   ./run-tests-safe.sh especiales # Solo casos especiales"
echo "   ./run-tests-safe.sh headless   # Modo headless"
echo "   ./run-tests-safe.sh debug      # Con navegador visible"
echo "   ./run-tests-safe.sh            # Todas las pruebas"

exit $exit_code
