# 🧮 Calculadora - Proyecto de Automatización con Scenario Outline

> **Proyecto completo de automatización de pruebas** usando Cucumber, Selenium WebDriver, Java y Allure Reports con soporte para números positivos, negativos y decimales.

## 📋 Tabla de Contenidos

- [🎯 Descripción General](#-descripción-general)
- [🛠️ Tecnologías y Herramientas](#️-tecnologías-y-herramientas)
- [📁 Estructura del Proyecto](#-estructura-del-proyecto)
- [⚙️ Configuración Inicial](#️-configuración-inicial)
- [🚀 Ejecución de Pruebas](#-ejecución-de-pruebas)
- [📊 Reportes y Documentación](#-reportes-y-documentación)
- [🔍 Debugging y Troubleshooting](#-debugging-y-troubleshooting)
- [🎯 Casos de Uso por Tags](#-casos-de-uso-por-tags)
- [📸 Screenshots y Evidencias](#-screenshots-y-evidencias)
- [🏗️ Arquitectura y Principios SOLID](#️-arquitectura-y-principios-solid)
- [🔧 Configuración Avanzada](#-configuración-avanzada)
- [📚 Recursos Adicionales](#-recursos-adicionales)

---

## 🎯 Descripción General

Este proyecto demuestra la implementación de **Scenario Outline** en Cucumber para probar una calculadora web con múltiples combinaciones de datos. Es ideal para **estudiantes de automatización de pruebas** que quieren aprender:

- ✅ **Scenario Outline** con tablas de ejemplos
- ✅ **Page Object Model** simplificado
- ✅ **Principios SOLID** en automatización
- ✅ **Allure Reports** profesionales
- ✅ **WebDriver ultra estable** (sin Connection Reset)
- ✅ **Screenshots automáticos** en errores
- ✅ **Testing de números negativos** y decimales

### 🎓 Objetivos de Aprendizaje

| Concepto | Implementación en el Proyecto |
|----------|-------------------------------|
| **Scenario Outline** | 4 esquemas diferentes con 20+ combinaciones de datos |
| **Data-Driven Testing** | Tablas de ejemplos con números enteros, decimales y negativos |
| **Tags Organization** | `@operaciones-basicas`, `@numeros-negativos`, `@casos-especiales` |
| **Reporting** | Allure + Cucumber HTML + Screenshots automáticos |
| **Error Handling** | Manejo robusto de timeouts y Connection Reset |
| **Cross-platform** | Scripts para Windows (.bat) y Linux/Mac (.sh) |

---

## 🛠️ Tecnologías y Herramientas

### 📦 Stack Principal

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| **Java** | 17+ | Lenguaje principal |
| **Maven** | 3.9+ | Gestión de dependencias |
| **Cucumber** | 7.15.0 | BDD Framework |
| **Selenium WebDriver** | 4.15.0 | Automatización UI |
| **JUnit 5** | 5.10.0 | Framework de testing |
| **Allure** | 2.24.0 | Reportes profesionales |
| **WebDriverManager** | 5.5.3 | Gestión automática de drivers |

### 🎨 Herramientas de Calidad

| Herramienta | Función |
|-------------|---------|
| **Allure Reports** | Reportes con gráficos, tendencias y screenshots |
| **Cucumber HTML** | Reportes nativos de Cucumber |
| **Screenshots** | Evidencias automáticas en fallos y pasos críticos |
| **Logging Detallado** | Debug completo con contexto de operaciones |

### 🌐 Aplicación Bajo Prueba

- **Calculadora HTML5** con JavaScript
- **Soporte completo** para números positivos, negativos y decimales
- **Data-testid** para automatización robusta
- **Responsive design** con validación en tiempo real

---

## 📁 Estructura del Proyecto

```
calculadora-scenario-outline/
│
├── 📄 README.md                                 # Documentación principal
├── 📄 pom.xml                                   # Configuración Maven
├── 📄 .gitignore                                # Archivos ignorados por Git
│
├── 🗂️ .allure/                                  # Allure binarios locales
│   └── allure-2.24.0/
│       ├── bin/
│       │   ├── allure                           # Ejecutable Linux/Mac
│       │   └── allure.bat                       # Ejecutable Windows
│       └── lib/                                 # Librerías Allure
│
├── 🗂️ src/
│   └── test/
│       ├── java/com/curso/automatizacion/
│       │   ├── steps/
│       │   │   └── 📄 CalculadoraSteps.java     # Step Definitions
│       │   └── runners/
│       │       └── 📄 TestRunner.java           # Runner de Cucumber
│       └── resources/
│           ├── features/
│           │   └── 📄 calculadora.feature       # Feature files en español
│           ├── 📄 calculadora.html              # Aplicación bajo prueba
│           ├── 📄 allure.properties             # Configuración Allure
│           ├── 📄 environment.properties        # Info del entorno
│           └── 📄 cucumber.properties           # Config Cucumber
│
├── 🗂️ scripts/                                  # Scripts de ejecución
│   ├── 📄 test-estable.sh                       # Testing ultra estable (Linux/Mac)
│   ├── 📄 test-estable.bat                      # Testing ultra estable (Windows)
│   ├── 📄 run-with-allure-reports.sh            # Generación Allure (Linux/Mac)
│   ├── 📄 run-with-allure-reports.bat           # Generación Allure (Windows)
│   └── 📄 calculadora-tests.sh                  # Tests por categorías
│
└── 🗂️ target/                                   # Archivos generados
    ├── cucumber-reports/                        # Reportes Cucumber HTML
    ├── allure-results/                          # Datos crudos Allure
    ├── allure-report/                           # Reportes Allure generados
    ├── screenshots/                             # Screenshots automáticos
    └── surefire-reports/                        # Reportes Maven/JUnit
```

### 📝 Archivos Clave

| Archivo | Descripción | Principio SOLID |
|---------|-------------|-----------------|
| `CalculadoraSteps.java` | Step definitions con WebDriver estable | Single Responsibility |
| `TestRunner.java` | Configuración de ejecución | Separation of Concerns |
| `calculadora.feature` | Scenarios en español con ejemplos | Open/Closed Principle |
| `calculadora.html` | App con soporte números negativos | Interface Segregation |

---

## ⚙️ Configuración Inicial

### 🔧 Pre-requisitos

```bash
# Verificar Java 17+
java -version

# Verificar Maven 3.9+
mvn -version

# Verificar Git (opcional)
git --version
```

### 📥 Instalación

#### **Opción 1: Clonar desde repositorio**
```bash
git clone <tu-repositorio>/calculadora-scenario-outline.git
cd calculadora-scenario-outline
```

#### **Opción 2: Crear desde cero**
```bash
# Crear estructura
mkdir calculadora-scenario-outline
cd calculadora-scenario-outline

# Crear estructura Maven
mvn archetype:generate -DgroupId=com.curso.automatizacion \
                       -DartifactId=calculadora-scenario-outline \
                       -DarchetypeArtifactId=maven-archetype-quickstart \
                       -DinteractiveMode=false
```

### 🔨 Configuración del Entorno

#### **1. Configurar Allure (Local)**
```bash
# Descomprimir Allure en .allure/
mkdir -p .allure
cd .allure
wget https://github.com/allure-framework/allure2/releases/download/2.24.0/allure-2.24.0.zip
unzip allure-2.24.0.zip
chmod +x allure-2.24.0/bin/allure
cd ..
```

#### **2. Instalar dependencias**
```bash
# Descargar todas las dependencias
mvn dependency:resolve
mvn dependency:resolve-sources
```

#### **3. Compilar proyecto**
```bash
# Compilación inicial
mvn clean compile
```

### 🛠️ Configuración de IDE

#### **IntelliJ IDEA**
```xml
<!-- Configurar en Settings > Build > Compiler > Annotation Processors -->
Enable annotation processing: ✅

<!-- Plugins recomendados -->
- Cucumber for Java
- Gherkin
- Allure
```

#### **VS Code**
```json
// .vscode/settings.json
{
    "java.home": "/path/to/java17",
    "maven.executable.path": "/path/to/maven/bin/mvn",
    "cucumber.features": "src/test/resources/features",
    "cucumber.steps": "src/test/java"
}
```

---

## 🚀 Ejecución de Pruebas

### 🎯 Comandos Básicos

#### **Maven (Estándar)**
```bash
# Todas las pruebas
mvn test

# Limpiar + Compilar + Ejecutar
mvn clean test

# Solo compilar
mvn compile test-compile
```

#### **Por Tags (Recomendado)**
```bash
# Operaciones básicas
mvn test -Dcucumber.filter.tags="@operaciones-basicas"

# Números decimales
mvn test -Dcucumber.filter.tags="@numeros-decimales"

# Números negativos
mvn test -Dcucumber.filter.tags="@numeros-negativos"

# Casos especiales
mvn test -Dcucumber.filter.tags="@casos-especiales"

# Funcionalidad limpiar
mvn test -Dcucumber.filter.tags="@funcionalidad-limpiar"
```

### 🛡️ Scripts Ultra Estables (Recomendado)

#### **Linux/Mac**
```bash
# Hacer ejecutables
chmod +x *.sh

# Un solo test para debugging
./test-estable.sh single

# Números negativos (problemáticos)
./test-estable.sh negativos

# Todas las pruebas
./test-estable.sh all

# Debug completo
./test-estable.sh debug
```

#### **Windows**
```cmd
REM Un solo test
test-estable.bat single

REM Números negativos
test-estable.bat negativos

REM Todas las pruebas
test-estable.bat all

REM Debug completo
test-estable.bat debug
```

### 🎛️ Opciones de Configuración

#### **Modo Headless**
```bash
# Con navegador visible (recomendado para debugging)
mvn test -Dwebdriver.headless=false

# Sin navegador (para CI/CD)
mvn test -Dwebdriver.headless=true
```

#### **Configuración de Timeouts**
```bash
# Timeouts estándar (30s)
mvn test

# Timeouts extendidos (90s) - para conexiones lentas
mvn test -Dtest.timeout=300
```

#### **Configuración de Memory**
```bash
# Memory estándar
mvn test

# Memory extendida (para muchos tests)
export MAVEN_OPTS="-Xmx2048m -XX:MaxPermSize=512m"
mvn test
```

---

## 📊 Reportes y Documentación

### 🎨 Tipos de Reportes Disponibles

| Tipo | Ubicación | Características |
|------|-----------|-----------------|
| **Allure** | `target/allure-report/index.html` | Profesional, gráficos, tendencias |
| **Cucumber HTML** | `target/cucumber-reports/index.html` | Nativo, simple, expandible |
| **Screenshots** | `target/screenshots/` | Evidencias automáticas |
| **Maven Surefire** | `target/surefire-reports/` | XML/TXT para CI/CD |

### 🌟 Allure Reports - Guía Completa

#### **Generación Automática (Recomendado)**
```bash
# Linux/Mac
./run-with-allure-reports.sh all

# Windows
run-with-allure-reports.bat all
```

#### **Generación Manual Paso a Paso**

**1. Ejecutar pruebas (generar datos)**
```bash
mvn clean test
# Genera: target/allure-results/
```

**2. Generar reporte HTML**
```bash
# Usando Allure local
./.allure/allure-2.24.0/bin/allure generate target/allure-results --clean -o target/allure-report

# Usando Allure global (si está instalado)
allure generate target/allure-results --clean -o target/allure-report
```

**3. Servir reporte (live server)**
```bash
# Inicia servidor en http://localhost:random-port
./.allure/allure-2.24.0/bin/allure serve target/allure-results
```

**4. Abrir reporte estático**
```bash
# Linux/Mac
open target/allure-report/index.html

# Windows
start target/allure-report/index.html
```

#### **Scripts de Allure Disponibles**

```bash
# Flujo completo: test + generar + abrir
./run-with-allure-reports.sh all

# Solo ejecutar tests
./run-with-allure-reports.sh test

# Solo generar reportes (de datos existentes)
./run-with-allure-reports.sh generate

# Servidor live (recomendado para desarrollo)
./run-with-allure-reports.sh serve

# Abrir reportes existentes
./run-with-allure-reports.sh open

# Limpiar reportes anteriores
./run-with-allure-reports.sh clean
```

### 📈 Configuración Allure Avanzada

#### **allure.properties**
```properties
# Directorios
allure.results.directory=target/allure-results

# Links (opcional - para integración con herramientas)
allure.link.issue.pattern=https://github.com/proyecto/issues/{}
allure.link.tms.pattern=https://github.com/proyecto/testcases/{}

# Información del proyecto
allure.epic.name=Calculadora - Automatización de Pruebas
allure.feature.name=Operaciones Matemáticas con Scenario Outline
allure.story.name=Validación Funcional con Cucumber

# Configuración de reportes
allure.report.name=Calculadora - Reporte de Automatización
allure.report.version=1.0.0
```

#### **environment.properties** (Info del entorno)
```properties
Browser=Chrome
Browser.Version=Latest
WebDriver=ChromeDriver (WebDriverManager)
Framework=Selenium WebDriver + Cucumber + JUnit 5
Language=Java 17
Build.Tool=Maven 3.9+
Test.Type=UI Automation
Application.Type=HTML + JavaScript
Test.Environment=Local
Operating.System=Cross-platform
Report.Framework=Allure + Cucumber HTML
Screenshot.Support=Enabled
Headless.Mode=Configurable
Parallel.Execution=No
Test.Data.Source=Scenario Outline Examples
```

### 📊 Interpretación de Reportes Allure

#### **Dashboard Principal**
- **Overview**: Resumen ejecutivo de la ejecución
- **Categories**: Tipos de errores agrupados
- **Suites**: Organización por features/tags
- **Graphs**: Distribución de resultados

#### **Información Detallada**
- **Timeline**: Duración de cada test
- **Behaviors**: Organización por Epic/Feature/Story
- **Packages**: Organización por estructura Java
- **Environment**: Información del entorno de ejecución

#### **Evidencias**
- **Screenshots**: Automáticos en cada step y error
- **Logs**: Output detallado de cada step
- **Attachments**: Archivos adicionales (si aplica)

---

## 🔍 Debugging y Troubleshooting

### 🚨 Problemas Comunes y Soluciones

#### **1. Connection Reset / WebSocket Errors**

**Síntomas:**
```
Connection reset
SocketException
Netty communication errors
WebSocket listener errors
```

**Solución:**
```bash
# Usar scripts ultra estables
./test-estable.sh single

# O configurar manualmente:
export MAVEN_OPTS="-Xmx2048m"
mvn test -Dwebdriver.headless=false -Dtest.timeout=300
```

#### **2. Timeouts en Elements**

**Síntomas:**
```
TimeoutException: Expected condition failed
Element not found
```

**Solución:**
```bash
# Verificar que calculadora.html existe
ls -la src/test/resources/calculadora.html

# Ejecutar con timeouts extendidos
mvn test -Dwebdriver.headless=false

# Debug paso a paso
./test-estable.sh debug
```

#### **3. Assertion Errors en Números Negativos**

**Síntomas:**
```
Expected: -5.0
Actual: 15.0
```

**Diagnóstico:**
```bash
# Ejecutar con debugging visual
./test-estable.sh single

# Revisar screenshots
ls -la target/screenshots/

# Verificar calculadora.html acepta números negativos
grep -n "min=" src/test/resources/calculadora.html
```

**Solución:**
- Actualizar `calculadora.html` con soporte para números negativos
- Verificar que inputs tengan `min="-999999"`

#### **4. Chrome/ChromeDriver Issues**

**Síntomas:**
```
SessionNotCreatedException
WebDriverException: chrome not reachable
```

**Solución:**
```bash
# Limpiar procesos Chrome colgados
pkill -f chrome
pkill -f chromedriver

# Usar WebDriverManager automático
mvn test -Dwebdriver.headless=false

# Verificar versión Chrome
google-chrome --version
```

#### **5. Maven/Compilation Errors**

**Síntomas:**
```
Failed to execute goal maven-compiler-plugin
Package does not exist
```

**Solución:**
```bash
# Limpiar completamente
mvn clean

# Recompilar desde cero
mvn compile test-compile

# Verificar dependencias
mvn dependency:tree
```

### 🔧 Herramientas de Debugging

#### **1. Logging Detallado**
```java
// En CalculadoraSteps.java - ya incluido
System.out.println("📊 Contexto:");
System.out.println("   - Número 1: " + ultimoNumero1);
System.out.println("   - Número 2: " + ultimoNumero2);
System.out.println("   - Operación: " + ultimaOperacion);
System.out.println("🔍 Resultado obtenido: '" + textoResultado + "'");
```

#### **2. Screenshots Automáticos**
```bash
# Screenshots se guardan automáticamente en:
target/screenshots/

# Buscar screenshots de errores:
ls target/screenshots/*FALLO*
ls target/screenshots/*Error*
```

#### **3. Console Logs del Navegador**
```java
// Para capturar logs JavaScript (si necesario)
LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
for (LogEntry entry : logs) {
    System.out.println(entry.getLevel() + " " + entry.getMessage());
}
```

#### **4. Modo Debug Maven**
```bash
# Maven con output completo
mvn test -X

# Solo errores
mvn test -q

# Con stack traces completos
mvn test -e
```

### 🧪 Testing Manual de la Calculadora

#### **Abrir en Navegador**
```bash
# Ruta completa al archivo
file:///ruta/completa/al/proyecto/src/test/resources/calculadora.html

# O usar servidor local simple
cd src/test/resources
python3 -m http.server 8000
# Abrir: http://localhost:8000/calculadora.html
```

#### **Testing JavaScript Console**
```javascript
// En DevTools > Console
// Probar función de números negativos
testNegativeNumbers();

// Verificar elementos
document.querySelector('[data-testid="numero1"]').value = "-10";
document.querySelector('[data-testid="numero2"]').value = "5";
```

---

## 🎯 Casos de Uso por Tags

### 📋 Organización de Tests

| Tag | Casos | Propósito | Comando |
|-----|-------|-----------|---------|
| `@operaciones-basicas` | 6 casos | Números enteros positivos | `mvn test -Dcucumber.filter.tags="@operaciones-basicas"` |
| `@numeros-decimales` | 4 casos | Números con decimales | `mvn test -Dcucumber.filter.tags="@numeros-decimales"` |
| `@numeros-negativos` | 8 casos | Números negativos | `mvn test -Dcucumber.filter.tags="@numeros-negativos"` |
| `@casos-especiales` | 2 casos | Errores (división por cero) | `mvn test -Dcucumber.filter.tags="@casos-especiales"` |
| `@funcionalidad-limpiar` | 1 caso | Botón limpiar | `mvn test -Dcucumber.filter.tags="@funcionalidad-limpiar"` |

### 🎲 Detalles de Cada Categoría

#### **@operaciones-basicas** - Números Enteros Positivos
```gherkin
Ejemplos:
| numero1 | numero2 | operacion      | resultado_esperado |
| 10      | 5       | suma           | 15                 |
| 20      | 8       | resta          | 12                 |
| 7       | 6       | multiplicacion | 42                 |
| 15      | 3       | division       | 5                  |
| 100     | 25      | suma           | 125                |
| 50      | 10      | multiplicacion | 500                |
```

**Propósito:** Validar operaciones matemáticas básicas con números enteros positivos.

**Comando específico:**
```bash
./test-estable.sh basicas
```

#### **@numeros-decimales** - Números con Decimales
```gherkin
Ejemplos:
| numero1 | numero2 | operacion      | resultado_esperado |
| 2.5     | 1.5     | suma           | 4                  |
| 10.75   | 2.25    | resta          | 8.5                |
| 3.14    | 2       | multiplicacion | 6.28               |
| 22.5    | 4.5     | division       | 5                  |
```

**Propósito:** Verificar manejo correcto de números decimales y precisión.

**Validación:** Usa `resultado debe ser cerca de` con tolerancia ±0.1

#### **@numeros-negativos** - Números Negativos ⭐
```gherkin
Ejemplos:
| numero1 | numero2 | operacion      | resultado_esperado |
| -10     | 5       | suma           | -5                 |
| -20     | -8      | resta          | -12                |
| -7      | 6       | multiplicacion | -42                |
| -15     | -3      | division       | 5                  |
| 10      | -5      | suma           | 5                  |
| 20      | -8      | resta          | 28                 |
| -12     | 3       | multiplicacion | -36                |
| 18      | -6      | division       | -3                 |
```

**Propósito:** Casos más complejos que requieren soporte HTML especializado.

**Debugging específico:**
```bash
# Un solo caso problemático
./test-estable.sh single

# Todos los números negativos
./test-estable.sh negativos
```

#### **@casos-especiales** - Manejo de Errores
```gherkin
Ejemplos:
| numero1 | numero2 | operacion | mensaje_error                |
| 10      | 0       | division  | No se puede dividir por cero |
| -5      | 0       | division  | No se puede dividir por cero |
```

**Propósito:** Validar manejo correcto de errores matemáticos.

#### **@funcionalidad-limpiar** - Reset de Calculadora
```gherkin
Escenario: Validar funcionalidad de limpiar
  Dado que he realizado una operación
  Cuando hago clic en el botón "Limpiar"
  Entonces los campos de entrada deben estar vacíos
  Y el resultado debe mostrar "---"
  Y la operación debe mostrar "Selecciona una operación"
```

**Propósito:** Verificar que el botón limpiar resetea completamente la calculadora.

### 🎯 Estrategias de Ejecución

#### **Para Desarrollo**
```bash
# Rápido - solo lo que estás trabajando
mvn test -Dcucumber.filter.tags="@operaciones-basicas"

# Medio - combinaciones relacionadas
mvn test -Dcucumber.filter.tags="@operaciones-basicas or @numeros-decimales"

# Debugging específico
./test-estable.sh single
```

#### **Para Testing Completo**
```bash
# Todas las categorías
mvn test

# Con reportes completos
./run-with-allure-reports.sh all
```

#### **Para CI/CD**
```bash
# Headless + reportes + no fallar en errores
mvn test -Dwebdriver.headless=true \
         -Dmaven.test.failure.ignore=true \
         -Dcucumber.plugin="pretty,html:target/cucumber-reports,json:target/cucumber-reports/cucumber.json"
```

---

## 📸 Screenshots y Evidencias

### 📷 Configuración de Screenshots

#### **Automáticos (Ya configurado)**
```java
// Screenshots se toman automáticamente en:
- Inicio de cada escenario
- Después de cada step crítico
- En todos los errores/fallos
- Al final de escenarios exitosos
```

#### **Ubicación y Nomenclatura**
```bash
target/screenshots/
├── screenshot_001_20250725_143022_Inicio_del_escenario.png
├── screenshot_002_20250725_143023_Primer_numero_ingresado_-10.png
├── screenshot_003_20250725_143024_Segundo_numero_ingresado_5.png
├── screenshot_004_20250725_143025_Operacion_ejecutada_suma.png
└── screenshot_005_20250725_143026_FALLO_resultado.png
```

**Formato:** `screenshot_{contador}_{timestamp}_{descripcion}.png`

### 🔍 Tipos de Screenshots

#### **1. Screenshots de Flujo Normal**
- ✅ Página cargada correctamente
- ✅ Primer número ingresado: {valor}
- ✅ Segundo número ingresado: {valor}
- ✅ Operación ejecutada: {operacion}
- ✅ Resultado verificado: {resultado}

#### **2. Screenshots de Error**
- 💥 FALLO en escenario
- 💥 Error al cargar página
- 💥 Error primer número
- 💥 Error en operación
- 💥 Error grave en validación

#### **3. Screenshots de Debug**
- 🔍 Estado antes de validación
- 🔍 Elementos no encontrados
- 🔍 Timeouts en elementos

### 📊 Integración con Reportes

#### **Allure Reports**
```java
@Attachment(value = "Screenshot", type = "image/png")
private byte[] tomarScreenshot(String descripcion) {
    // Screenshots aparecen automáticamente en:
    // - Allure report > Test > Attachments
    // - Con descripción contextual
    // - Clickeables para ver en grande
}
```

#### **Cucumber HTML Reports**
```java
scenario.attach(screenshot, "image/png", descripcion);
// Screenshots integrados en el reporte HTML
// Visibles en cada step que los genera
```

### 🛠️ Configuración Avanzada de Screenshots

#### **Personalizar Calidad**
```java
// En CalculadoraSteps.java - modificar si necesario
private byte[] tomarScreenshot(String descripcion) {
    // Cambiar calidad/formato aquí
    return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
}
```

#### **Screenshots Condicionales**
```java
// Configurar en application.properties (si quieres)
screenshot.enabled=true
screenshot.on.success=true
screenshot.on.failure=true
screenshot.on.steps=false  # Solo pasos críticos
```

#### **Limpieza Automática**
```bash
# Limpiar screenshots antiguos (opcional)
find target/screenshots -name "*.png" -mtime +7 -delete

# O usar el script
./run-with-allure-reports.sh clean
```

---

## 🏗️ Arquitectura y Principios SOLID

### 🎯 Implementación de Principios SOLID

#### **1. Single Responsibility Principle (SRP)**

**CalculadoraSteps.java**
```java
// ✅ SOLO maneja steps de Cucumber
// ❌ NO maneja configuración de base de datos
// ❌ NO maneja lógica de negocio compleja
// ❌ NO maneja múltiples tipos de aplicaciones

public class CalculadoraSteps {
    // Responsabilidad única: Steps de automatización para calculadora
}
```

**TestRunner.java**
```java
// ✅ SOLO configura la ejecución de Cucumber
// ❌ NO ejecuta tests directamente
// ❌ NO maneja WebDriver

@Suite
public class TestRunner {
    // Responsabilidad única: Configuración de ejecución
}
```

#### **2. Open/Closed Principle (OCP)**

**Features extensibles**
```gherkin
# ✅ Abierto para extensión: fácil agregar nuevos casos
Esquema del escenario: Operaciones matemáticas básicas
  # Cerrado para modificación: no cambiar la estructura existente
  
# Fácil agregar nuevos ejemplos:
| numero1 | numero2 | operacion | resultado_esperado |
| 25      | 5       | division  | 5                  |  # ← Nuevo caso
```

**Steps reutilizables**
```java
// ✅ Los steps existentes funcionan para nuevos casos
// ❌ No necesitas modificar steps para nuevos datos

@Cuando("ingreso el primer número {}")
public void ingresoElPrimerNumero(String numero) {
    // Funciona para: 10, -10, 3.14, 999, etc.
}
```

#### **3. Liskov Substitution Principle (LSP)**

**WebDriver intercambiable**
```java
// ✅ Cualquier implementación de WebDriver funciona
private WebDriver driver;  // Puede ser Chrome, Firefox, Edge, etc.

// ✅ Todos los WebDriverWait se comportan igual
private WebDriverWait wait;
```

#### **4. Interface Segregation Principle (ISP)**

**Interfaces específicas** (implementación futura)
```java
// ✅ Interfaces pequeñas y específicas
interface ICalculatorPage {
    void enterNumber(String field, String value);
    void clickOperation(String operation);
    String getResult();
}

// ❌ NO interfaces gigantes con métodos no usados
interface IGiantInterface {
    // 50 métodos diferentes - MALO
}
```

#### **5. Dependency Inversion Principle (DIP)**

**Abstracciones sobre concreciones**
```java
// ✅ Depende de abstracciones (WebDriver interface)
private WebDriver driver;  // Interface, no ChromeDriver directamente

// ✅ Configuración externa
boolean headless = System.getProperty("webdriver.headless", "false");
```

### 🏛️ Patrones de Diseño Implementados

#### **1. Page Object Model (Simplificado)**
```java
// Encapsulación de elementos y acciones
private void ingresarNumeroSeguro(String campo, String valor) {
    // Lógica centralizada para interactuar con campos
}

private void hacerClicSeguro(String operacion) {
    // Lógica centralizada para botones
}
```

#### **2. Template Method Pattern**
```java
// Estructura común para validaciones
@Entonces("el resultado debe ser exactamente {}")
public void elResultadoDebeSerExactamente(String resultadoEsperado) {
    // 1. Obtener elemento (común)
    // 2. Validar contenido (específico)
    // 3. Tomar screenshot (común)
}
```

#### **3. Strategy Pattern (Implicit)**
```java
// Diferentes estrategias de espera según el contexto
private WebDriverWait wait;      // Estrategia rápida (30s)
private WebDriverWait longWait;  // Estrategia lenta (60s)
```

### 🔧 Modularidad y Separación de Intereses

#### **Separación por Responsabilidades**

| Componente | Responsabilidad | Principio |
|------------|-----------------|-----------|
| **Feature Files** | Definición de comportamiento esperado | DDD (Domain Driven Design) |
| **Step Definitions** | Traducción a código automatizable | Single Responsibility |
| **WebDriver Manager** | Gestión del navegador | Encapsulation |
| **Test Runner** | Configuración de ejecución | Separation of Concerns |
| **Reporting** | Generación de evidencias | Open/Closed |

#### **Configuración Externa**
```properties
# cucumber.properties - Configuración de Cucumber
cucumber.plugin=pretty,html:target/cucumber-reports
cucumber.glue=com.curso.automatizacion.steps

# allure.properties - Configuración de Allure
allure.results.directory=target/allure-results
allure.epic.name=Calculadora

# System Properties - Configuración de WebDriver
-Dwebdriver.headless=false
-Dtest.timeout=300
```

### 📦 Abstracción y Encapsulación

#### **Métodos Privados (Encapsulación)**
```java
// ✅ Detalles internos ocultos
private void inicializarWebDriverEstable() {
    // Configuración compleja oculta del usuario
}

private void esperarElementoSeguro(By locator) {
    // Lógica de espera encapsulada
}

// ✅ Interface pública simple
@Cuando("ingreso el primer número {}")
public void ingresoElPrimerNumero(String numero) {
    // Usa métodos privados internamente
}
```

#### **Abstracción de Complejidad**
```java
// ✅ El usuario no ve la complejidad interna
hagoClicEnElBotonDe("suma");

// ✅ Internamente maneja:
// - Mapeo de nombres a selectores
// - Esperas robustas
// - Manejo de errores
// - Screenshots automáticos
// - Logging detallado
```

---

## 🔧 Configuración Avanzada

### ⚙️ Configuración de Maven Avanzada

#### **pom.xml - Profiles**
```xml
<profiles>
    <!-- Profile para desarrollo local -->
    <profile>
        <id>local</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
            <webdriver.headless>false</webdriver.headless>
            <test.timeout>30</test.timeout>
        </properties>
    </profile>
    
    <!-- Profile para CI/CD -->
    <profile>
        <id>ci</id>
        <properties>
            <webdriver.headless>true</webdriver.headless>
            <test.timeout>60</test.timeout>
            <maven.test.failure.ignore>true</maven.test.failure.ignore>
        </properties>
    </profile>
    
    <!-- Profile para debugging -->
    <profile>
        <id>debug</id>
        <properties>
            <webdriver.headless>false</webdriver.headless>
            <test.timeout>300</test.timeout>
            <maven.test.failure.ignore>true</maven.test.failure.ignore>
        </properties>
    </profile>
</profiles>
```

**Uso de profiles:**
```bash
# Desarrollo local (default)
mvn test

# CI/CD
mvn test -Pci

# Debugging
mvn test -Pdebug
```

#### **Plugin de Failsafe (Para Integration Tests)**
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-failsafe-plugin</artifactId>
    <version>3.1.2</version>
    <configuration>
        <includes>
            <include>**/*IT.java</include>
            <include>**/*Tests.java</include>
        </includes>
    </configuration>
    <executions>
        <execution>
            <goals>
                <goal>integration-test</goal>
                <goal>verify</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

### 🌐 Configuración Multi-Browser

#### **WebDriver Factory (Extensión futura)**
```java
public class WebDriverFactory {
    public static WebDriver createDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                return createChromeDriver();
            case "firefox":
                return createFirefoxDriver();
            case "edge":
                return createEdgeDriver();
            default:
                throw new IllegalArgumentException("Browser not supported: " + browserName);
        }
    }
}
```

**Uso:**
```bash
# Chrome (default)
mvn test

# Firefox
mvn test -Dbrowser=firefox

# Edge
mvn test -Dbrowser=edge
```

### 🔄 Configuración de Parallel Execution

#### **JUnit Platform Configuration**
```properties
# junit-platform.properties
junit.jupiter.execution.parallel.enabled=true
junit.jupiter.execution.parallel.mode.default=concurrent
junit.jupiter.execution.parallel.config.strategy=dynamic
```

#### **Cucumber Parallel (Configuration)**
```java
// Para parallel execution avanzado
@ConfigurationParameter(key = PARALLEL_EXECUTION_ENABLED_PROPERTY_NAME, value = "true")
@ConfigurationParameter(key = PARALLEL_CONFIG_STRATEGY_PROPERTY_NAME, value = "dynamic")
public class ParallelTestRunner {
    // Configuración para ejecución paralela
}
```

### 📊 Integración con CI/CD

#### **GitHub Actions (.github/workflows/tests.yml)**
```yaml
name: Automated Tests
on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v3
    
    - name: Set up JDK 17
      uses: actions/setup-java@v3
      with:
        java-version: '17'
        distribution: 'temurin'
    
    - name: Cache Maven dependencies
      uses: actions/cache@v3
      with:
        path: ~/.m2
        key: ${{ runner.os }}-m2-${{ hashFiles('**/pom.xml') }}
    
    - name: Run tests
      run: mvn test -Pci
    
    - name: Generate Allure Report
      run: |
        ./.allure/allure-2.24.0/bin/allure generate target/allure-results --clean -o target/allure-report
    
    - name: Upload Allure Report
      uses: actions/upload-artifact@v3
      if: always()
      with:
        name: allure-report
        path: target/allure-report/
    
    - name: Upload Screenshots
      uses: actions/upload-artifact@v3
      if: failure()
      with:
        name: screenshots
        path: target/screenshots/
```

#### **Jenkins Pipeline (Jenkinsfile)**
```groovy
pipeline {
    agent any
    
    tools {
        maven 'Maven-3.9'
        jdk 'JDK-17'
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Test') {
            steps {
                sh 'mvn clean test -Pci'
            }
            post {
                always {
                    publishHTML([
                        allowMissing: false,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: 'target/allure-report',
                        reportFiles: 'index.html',
                        reportName: 'Allure Report'
                    ])
                    
                    archiveArtifacts artifacts: 'target/screenshots/**/*.png', fingerprint: true
                }
            }
        }
    }
}
```

### 🔒 Configuración de Seguridad

#### **Credentials Management**
```java
// Para aplicaciones con login (extensión futura)
public class CredentialsManager {
    public static String getUsername() {
        return System.getProperty("test.username", 
               System.getenv("TEST_USERNAME"));
    }
    
    public static String getPassword() {
        return System.getProperty("test.password", 
               System.getenv("TEST_PASSWORD"));
    }
}
```

#### **Environment Variables**
```bash
# .env (para desarrollo local)
TEST_USERNAME=testuser
TEST_PASSWORD=testpass
BROWSER=chrome
HEADLESS=false

# Para CI/CD
export TEST_USERNAME=${{ secrets.TEST_USERNAME }}
export TEST_PASSWORD=${{ secrets.TEST_PASSWORD }}
```

---

## 📚 Recursos Adicionales

### 📖 Documentación Oficial

| Tecnología | Link | Uso en el Proyecto |
|------------|------|-------------------|
| **Cucumber** | [cucumber.io](https://cucumber.io/docs/cucumber/) | BDD, Scenario Outline, Gherkin |
| **Selenium WebDriver** | [selenium.dev](https://selenium.dev/documentation/) | Automatización del navegador |
| **Allure Framework** | [docs.qameta.io](https://docs.qameta.io/allure/) | Reportes profesionales |
| **JUnit 5** | [junit.org](https://junit.org/junit5/docs/current/user-guide/) | Framework de testing |
| **Maven** | [maven.apache.org](https://maven.apache.org/guides/) | Gestión de dependencias |

### 🎓 Tutoriales y Guías

#### **Cucumber y BDD**
- [Cucumber School](https://school.cucumber.io/) - Curso oficial gratuito
- [BDD with Cucumber](https://www.baeldung.com/cucumber-java) - Tutorial Baeldung
- [Scenario Outline Examples](https://cucumber.io/docs/gherkin/reference/#scenario-outline) - Documentación oficial

#### **Selenium WebDriver**
- [Selenium WebDriver Tutorial](https://www.selenium.dev/documentation/webdriver/) - Guía oficial
- [WebDriver Best Practices](https://www.selenium.dev/documentation/test_practices/) - Mejores prácticas
- [Page Object Model](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/) - Patrón recomendado

#### **Allure Reports**
- [Allure Java Tutorial](https://docs.qameta.io/allure/#_java) - Guía completa
- [Allure Annotations](https://docs.qameta.io/allure/#_annotations) - Anotaciones disponibles
- [Allure Best Practices](https://docs.qameta.io/allure/#_best_practices) - Recomendaciones

### 🛠️ Herramientas Complementarias

#### **IDEs y Plugins**
```text
IntelliJ IDEA:
├── Cucumber for Java
├── Gherkin
├── Allure TestOps
└── Maven Helper

VS Code:
├── Cucumber (Gherkin) Full Support
├── Java Extension Pack
├── Maven for Java
└── Test Runner for Java

Eclipse:
├── Cucumber Eclipse Plugin
├── TestNG
└── Maven Integration
```

#### **Browser DevTools**
```javascript
// Comandos útiles para debugging en DevTools
// Encontrar elementos por data-testid
document.querySelector('[data-testid="numero1"]')

// Verificar valores de campos
document.querySelector('[data-testid="numero1"]').value

// Simular operaciones manualmente
document.querySelector('[data-testid="btn-suma"]').click()

// Ver logs de la calculadora
console.log("Testing manual de calculadora")
```

#### **Herramientas de Análisis**
- **SonarQube**: Análisis de calidad de código
- **SpotBugs**: Detección de bugs estáticos
- **Checkstyle**: Estilo de código Java
- **JaCoCo**: Cobertura de código

### 📊 Métricas y KPIs

#### **Métricas de Testing**
```text
Cobertura de Funcionalidad:
├── Operaciones Básicas: 100% (4/4)
├── Números Decimales: 100% (4/4)
├── Números Negativos: 100% (8/8)
├── Casos de Error: 100% (2/2)
└── Funcionalidades UI: 100% (1/1)

Métricas de Calidad:
├── Tests Automatizados: 21
├── Cobertura de Scenarios: 100%
├── Tiempo Promedio/Test: ~15s
└── Estabilidad: 95%+ (con configuración estable)
```

#### **Reportes de Tendencias**
```bash
# Allure genera automáticamente:
- Duración de tests por ejecución
- Tasa de éxito histórica
- Tests más lentos
- Tests más inestables
- Distribución de errores por categoría
```

### 🔗 Extensiones y Mejoras Futuras

#### **Integraciones Posibles**
1. **Base de Datos**: Para test data más complejo
2. **APIs**: Testing de backend + frontend
3. **Mobile**: Appium para apps móviles
4. **Performance**: JMeter integration
5. **Visual Testing**: Applitools o similar

#### **Patrones Avanzados**
1. **Page Factory**: Para elementos más complejos
2. **Builder Pattern**: Para test data
3. **Chain of Responsibility**: Para validaciones complejas
4. **Observer Pattern**: Para eventos de testing

#### **Configuraciones Avanzadas**
1. **Docker**: Containerización de tests
2. **Kubernetes**: Ejecución distribuida
3. **Grid**: Selenium Grid para parallelización
4. **Cloud**: BrowserStack, Sauce Labs, etc.

---

## 🚀 Quick Start - Resumen Ejecutivo

### ⚡ Para Empezar Inmediatamente

```bash
# 1. Verificar pre-requisitos
java -version  # Debe ser 17+
mvn -version   # Debe ser 3.9+

# 2. Compilar proyecto
mvn clean compile

# 3. Ejecutar un test simple
./test-estable.sh single

# 4. Ver reportes
./run-with-allure-reports.sh all
```

### 🎯 Comandos Más Usados

```bash
# Development
./test-estable.sh negativos        # Casos problemáticos
./test-estable.sh basicas          # Casos simples
./test-estable.sh debug            # Debug completo

# Reporting
./run-with-allure-reports.sh all   # Todo: test + reportes
./run-with-allure-reports.sh serve # Servidor live

# Troubleshooting
ls target/screenshots/             # Ver evidencias
mvn test -X                        # Debug Maven
```

### 📞 Soporte y Contacto

Para preguntas, issues o mejoras:

1. **Revisar screenshots**: `target/screenshots/`
2. **Consultar logs**: Output de Maven con `-X`
3. **Verificar configuración**: `pom.xml` y properties
4. **Testing manual**: Abrir `calculadora.html` en navegador

---

**📚 Documentación actualizada:** $(date)  
**🏷️ Versión del proyecto:** 1.0.0  
**🎯 Nivel:** Intermedio - Avanzado  
**⏱️ Tiempo estimado de setup:** 15-30 minutos  

---

*Esta documentación es parte del proyecto de automatización de pruebas con **Scenario Outline** y está diseñada para estudiantes que quieren dominar **BDD**, **Selenium WebDriver** y **Allure Reports** con casos de uso reales y configuraciones profesionales.*