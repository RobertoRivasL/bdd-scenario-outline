#!/bin/bash
echo "🎯 CALCULADORA - SCENARIO OUTLINE DEFINITIVO"
echo "==========================================="

# Función para mostrar ayuda
show_help() {
    echo "🔧 Comandos disponibles:"
    echo "   ./calculadora-tests.sh basicas     # Operaciones básicas (enteros)"
    echo "   ./calculadora-tests.sh decimales   # Números decimales"
    echo "   ./calculadora-tests.sh especiales  # Casos especiales y errores"
    echo "   ./calculadora-tests.sh limpiar     # Funcionalidad limpiar"
    echo "   ./calculadora-tests.sh all         # Todas las pruebas"
    echo "   ./calculadora-tests.sh headless    # Modo headless (sin navegador)"
    echo "   ./calculadora-tests.sh help        # Mostrar esta ayuda"
}

case "${1:-help}" in
    "basicas")
        echo "🔍 Ejecutando operaciones básicas..."
        mvn test -Dcucumber.filter.tags="@operaciones-basicas" -Dcucumber.plugin="pretty"
        ;;
    "decimales")
        echo "🔍 Ejecutando números decimales..."
        mvn test -Dcucumber.filter.tags="@numeros-decimales" -Dcucumber.plugin="pretty"
        ;;
    "especiales")
        echo "🔍 Ejecutando casos especiales..."
        mvn test -Dcucumber.filter.tags="@casos-especiales" -Dcucumber.plugin="pretty"
        ;;
    "limpiar")
        echo "🔍 Ejecutando funcionalidad limpiar..."
        mvn test -Dcucumber.filter.tags="@funcionalidad-limpiar" -Dcucumber.plugin="pretty"
        ;;
    "all")
        echo "🔍 Ejecutando TODAS las pruebas..."
        mvn test -Dcucumber.plugin="pretty"
        ;;
    "headless")
        echo "🔍 Ejecutando en modo headless..."
        mvn test -Dwebdriver.headless=true -Dcucumber.plugin="pretty"
        ;;
    "help"|*)
        show_help
        exit 0
        ;;
esac

exit_code=$?
echo ""
if [[ $exit_code -eq 0 ]]; then
    echo "✅ Pruebas completadas exitosamente"
    echo ""
    echo "🎓 Scenario Outline funcionando perfectamente:"
    echo "   ✅ Múltiples Examples con datos de prueba"
    echo "   ✅ Tags organizados para ejecución selectiva"
    echo "   ✅ Step Definitions únicos sin conflictos"
    echo "   ✅ Manejo de enteros y decimales"
    echo "   ✅ Validación de errores"
else
    echo "❌ Algunas pruebas fallaron (código: $exit_code)"
fi

echo ""
show_help
exit $exit_code
