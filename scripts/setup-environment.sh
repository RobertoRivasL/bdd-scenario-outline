// ==========================================
// SCRIPT DE LIMPIEZA Y SETUP
// ==========================================

// scripts/setup-environment.sh
#!/bin/bash

echo "🔧 Configurando entorno para Java 17 + Allure"
echo "=============================================="

# Crear directorios necesarios
echo "📁 Creando directorios..."
mkdir -p target/logs
mkdir -p target/screenshots
mkdir -p target/allure-results
mkdir -p target/cucumber-reports

# Limpiar directorios anteriores
echo "🧹 Limpiando directorios anteriores..."
rm -rf target/allure-results/*
rm -rf target/cucumber-reports/*
rm -rf target/screenshots/*
rm -rf target/logs/*

# Verificar Java 17
echo "☕ Verificando Java 17..."
java_version=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2)
echo "Java detectado: $java_version"

if [[ "$java_version" == 17* ]]; then
    echo "✅ Java 17 confirmado"
else
    echo "⚠️  Advertencia: Se recomienda Java 17"
fi

# Verificar Maven
echo "📦 Verificando Maven..."
mvn_version=$(mvn -version 2>&1 | head -n 1)
echo "Maven: $mvn_version"

echo ""
echo "✅ Entorno configurado correctamente"
echo ""
echo "🚀 Para ejecutar las pruebas:"
echo "   mvn clean test"
echo ""
echo "📊 Para generar reportes Allure:"
echo "   mvn allure:report"
echo "   mvn allure:serve"
echo ""
