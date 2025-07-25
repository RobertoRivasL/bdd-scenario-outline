#!/bin/bash

echo "🔄 PRUEBA RÁPIDA - Verificar Sincronización"
echo "=========================================="

echo "🧪 Ejecutando todas las pruebas..."

# Probar cada tipo
echo ""
echo "1️⃣ Probando operaciones básicas..."
mvn test -Dcucumber.filter.tags="@operaciones-basicas" -Dcucumber.plugin="pretty" -q
if [[ $? -eq 0 ]]; then
    echo "✅ Operaciones básicas: ÉXITO"
else
    echo "❌ Operaciones básicas: FALLÓ"
fi

echo ""
echo "2️⃣ Probando números decimales..."
mvn test -Dcucumber.filter.tags="@numeros-decimales" -Dcucumber.plugin="pretty" -q
if [[ $? -eq 0 ]]; then
    echo "✅ Números decimales: ÉXITO"
else
    echo "❌ Números decimales: FALLÓ"
fi

echo ""
echo "3️⃣ Probando casos especiales..."
mvn test -Dcucumber.filter.tags="@casos-especiales" -Dcucumber.plugin="pretty" -q
if [[ $? -eq 0 ]]; then
    echo "✅ Casos especiales: ÉXITO"
else
    echo "❌ Casos especiales: FALLÓ"
fi

echo ""
echo "4️⃣ Probando funcionalidad limpiar..."
mvn test -Dcucumber.filter.tags="@funcionalidad-limpiar" -Dcucumber.plugin="pretty" -q
if [[ $? -eq 0 ]]; then
    echo "✅ Funcionalidad limpiar: ÉXITO"
else
    echo "❌ Funcionalidad limpiar: FALLÓ"
fi

echo ""
echo "🎯 Ejecutando TODAS las pruebas juntas..."
mvn test -Dcucumber.plugin="pretty" -q
exit_code=$?

echo ""
if [[ $exit_code -eq 0 ]]; then
    echo "🎉 ¡TODAS LAS PRUEBAS PASARON!"
    echo "✅ Sincronización completada exitosamente"
    echo ""
    echo "📊 Para generar reportes:"
    echo "   ./run-with-screenshots.sh reports"
    echo ""
    echo "👁️ Para ver en modo visual:"
    echo "   ./run-with-screenshots.sh visual"
else
    echo "❌ Algunas pruebas fallaron"
    echo "🔍 Ejecuta con más detalles:"
    echo "   mvn test -Dcucumber.plugin=\"pretty\""
fi

exit $exit_code
