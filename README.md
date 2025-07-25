# 🧮 Calculadora - Demo Scenario Outline

## 📋 Descripción
Proyecto de automatización de pruebas con **Scenario Outline** usando Cucumber, Selenium WebDriver y Java.

## 🚀 Ejecución Rápida

```bash
# Compilar y ejecutar todas las pruebas
mvn clean test

# Solo operaciones básicas
mvn test -Dcucumber.filter.tags="@operaciones-basicas"

# Modo headless para CI/CD
mvn test -Dwebdriver.headless=true
```

## 📊 Reportes
Los reportes se generan en: `target/cucumber-reports/index.html`

## 🛠️ Tecnologías
- Java 11+
- Maven 3.6+
- Cucumber 7.18
- Selenium WebDriver 4.15
- JUnit 5

---
Generado automáticamente por setup-project.sh
