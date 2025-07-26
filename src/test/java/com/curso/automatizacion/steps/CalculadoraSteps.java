package com.curso.automatizacion.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.es.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Step Definitions con WebDriver ULTRA ESTABLE
 * Solucionando errores de Connection Reset y WebSocket
 */
public class CalculadoraSteps {
    
    private WebDriver driver;
    private WebDriverWait wait;
    private WebDriverWait longWait;
    private Scenario scenario;
    private static int screenshotCounter = 1;
    
    // Variables para debugging
    private String ultimoNumero1;
    private String ultimoNumero2;
    private String ultimaOperacion;

    @Before
    public void setUp(Scenario scenario) {
        this.scenario = scenario;
        System.out.println("\n" + "=".repeat(80));
        System.out.println("🚀 INICIANDO ESCENARIO: " + scenario.getName());
        System.out.println("=".repeat(80));
        
        try {
            inicializarWebDriverEstable();
            System.out.println("✅ WebDriver inicializado de forma estable");
            tomarScreenshot("🎬 Inicio del escenario");
        } catch (Exception e) {
            System.err.println("❌ ERROR al inicializar WebDriver: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Fallo en setup", e);
        }
    }

    @After
    public void tearDown() {
        System.out.println("\n" + "-".repeat(80));
        System.out.println("🏁 FINALIZANDO ESCENARIO: " + scenario.getName());
        
        if (scenario.isFailed()) {
            System.err.println("❌ ESCENARIO FALLÓ");
            System.err.println("📊 Datos del fallo:");
            System.err.println("   - Último número 1: " + ultimoNumero1);
            System.err.println("   - Último número 2: " + ultimoNumero2);
            System.err.println("   - Última operación: " + ultimaOperacion);
            tomarScreenshot("💥 FALLO en escenario");
        } else {
            System.out.println("✅ ESCENARIO EXITOSO");
            tomarScreenshot("🎉 Escenario completado exitosamente");
        }
        
        // Cerrar de forma muy gradual para evitar Connection Reset
        cerrarWebDriverSeguro();
        System.out.println("-".repeat(80) + "\n");
    }

    // ===== STEPS OPTIMIZADOS =====

    @Step("Navegar a la página de la calculadora")
    @Dado("que estoy en la página de la calculadora")
    public void queEstoyEnLaPaginaDeLaCalculadora() {
        System.out.println("\n📍 STEP: Navegando a la página de la calculadora");
        
        String rutaAplicacion = "file://" + System.getProperty("user.dir") + "/src/test/resources/calculadora.html";
        System.out.println("🌐 URL: " + rutaAplicacion);
        
        try {
            // Navegación con reintentos
            navegarConReintentos(rutaAplicacion, 3);
            
            WebElement titulo = longWait.until(
                ExpectedConditions.presenceOfElementLocated(By.tagName("h1"))
            );
            
            String textoTitulo = titulo.getText();
            System.out.println("📖 Título encontrado: '" + textoTitulo + "'");
            
            assertTrue(textoTitulo.contains("Calculadora"), 
                "❌ FALLO: El título '" + textoTitulo + "' no contiene 'Calculadora'");
            
            tomarScreenshot("✅ Página cargada correctamente");
            esperarSeguro(1000);
            System.out.println("✅ Página de calculadora cargada exitosamente");
            
        } catch (Exception e) {
            System.err.println("❌ ERROR al cargar la página:");
            System.err.println("   - Mensaje: " + e.getMessage());
            System.err.println("   - Tipo: " + e.getClass().getSimpleName());
            tomarScreenshot("💥 Error al cargar página");
            throw new AssertionError("Fallo al cargar página de calculadora: " + e.getMessage(), e);
        }
    }

    @Step("Realizar operación previa")
    @Dado("que he realizado una operación")
    public void queHeRealizadoUnaOperacion() {
        System.out.println("\n📍 STEP: Realizando operación previa (setup)");
        
        try {
            ingresarNumeroSeguro("numero1", "10");
            ingresarNumeroSeguro("numero2", "5");
            hacerClicSeguro("suma");
            tomarScreenshot("✅ Operación previa completada");
            System.out.println("✅ Operación previa completada: 10 + 5");
        } catch (Exception e) {
            System.err.println("❌ ERROR en operación previa: " + e.getMessage());
            tomarScreenshot("💥 Error en operación previa");
            throw new AssertionError("Fallo en operación previa", e);
        }
    }

    @Step("Ingresar primer número: {numero}")
    @Cuando("ingreso el primer número {}")
    public void ingresoElPrimerNumero(String numero) {
        System.out.println("\n📍 STEP: Ingresando primer número = " + numero);
        ultimoNumero1 = numero;
        
        try {
            ingresarNumeroSeguro("numero1", numero);
            tomarScreenshot("✅ Primer número ingresado: " + numero);
            System.out.println("✅ Primer número ingresado correctamente");
        } catch (Exception e) {
            System.err.println("❌ ERROR al ingresar primer número '" + numero + "': " + e.getMessage());
            tomarScreenshot("💥 Error primer número");
            throw new AssertionError("Fallo al ingresar primer número: " + numero, e);
        }
    }

    @Step("Ingresar segundo número: {numero}")
    @Y("ingreso el segundo número {}")
    public void ingresoElSegundoNumero(String numero) {
        System.out.println("\n📍 STEP: Ingresando segundo número = " + numero);
        ultimoNumero2 = numero;
        
        try {
            ingresarNumeroSeguro("numero2", numero);
            tomarScreenshot("✅ Segundo número ingresado: " + numero);
            System.out.println("✅ Segundo número ingresado correctamente");
        } catch (Exception e) {
            System.err.println("❌ ERROR al ingresar segundo número '" + numero + "': " + e.getMessage());
            tomarScreenshot("💥 Error segundo número");
            throw new AssertionError("Fallo al ingresar segundo número: " + numero, e);
        }
    }

    @Step("Hacer clic en operación: {operacion}")
    @Y("hago clic en el botón de {word}")
    public void hagoClicEnElBotonDe(String operacion) {
        System.out.println("\n📍 STEP: Ejecutando operación = " + operacion);
        ultimaOperacion = operacion;
        
        try {
            hacerClicSeguro(operacion);
            tomarScreenshot("✅ Operación ejecutada: " + operacion);
            System.out.println("✅ Operación ejecutada correctamente");
        } catch (Exception e) {
            System.err.println("❌ ERROR al ejecutar operación '" + operacion + "': " + e.getMessage());
            tomarScreenshot("💥 Error en operación");
            throw new AssertionError("Fallo al ejecutar operación: " + operacion, e);
        }
    }

    @Step("Hacer clic en botón: {nombreBoton}")
    @Cuando("hago clic en el botón {string}")
    public void hagoClicEnElBoton(String nombreBoton) {
        System.out.println("\n📍 STEP: Haciendo clic en botón = " + nombreBoton);
        
        try {
            switch (nombreBoton.toLowerCase()) {
                case "limpiar":
                    hacerClicSeguro("limpiar");
                    break;
                case "=":
                case "igual":
                    hacerClicSeguro("igual");
                    break;
                default:
                    throw new IllegalArgumentException("Botón no reconocido: " + nombreBoton);
            }
            
            tomarScreenshot("✅ Botón presionado: " + nombreBoton);
            System.out.println("✅ Botón presionado correctamente");
        } catch (Exception e) {
            System.err.println("❌ ERROR al hacer clic en botón '" + nombreBoton + "': " + e.getMessage());
            tomarScreenshot("💥 Error en botón");
            throw new AssertionError("Fallo al hacer clic en botón: " + nombreBoton, e);
        }
    }

    // ===== VALIDACIONES OPTIMIZADAS =====

    @Step("Verificar resultado exacto: {resultadoEsperado}")
    @Entonces("el resultado debe ser exactamente {}")
    public void elResultadoDebeSerExactamente(String resultadoEsperado) {
        System.out.println("\n📍 VALIDACIÓN: Verificando resultado exacto = " + resultadoEsperado);
        System.out.println("📊 Contexto de la operación:");
        System.out.println("   - Número 1: " + ultimoNumero1);
        System.out.println("   - Número 2: " + ultimoNumero2);
        System.out.println("   - Operación: " + ultimaOperacion);
        System.out.println("   - Resultado esperado: " + resultadoEsperado);
        
        try {
            WebElement resultado = esperarElementoSeguro(By.cssSelector("[data-testid='resultado-valor']"));
            
            // Esperar más tiempo para que aparezca el resultado
            esperarSeguro(2000);
            String textoResultado = resultado.getText().trim();
            
            System.out.println("🔍 Resultado obtenido: '" + textoResultado + "'");
            
            // Validación de errores
            if (textoResultado.contains("Error") || textoResultado.contains("error")) {
                System.err.println("❌ FALLO: Se encontró un error en el resultado");
                tomarScreenshot("💥 Error en resultado");
                fail("❌ ERROR INESPERADO en resultado: '" + textoResultado + 
                     "' para operación: " + ultimoNumero1 + " " + ultimaOperacion + " " + ultimoNumero2);
            }
            
            // Validación del resultado esperado
            try {
                double esperado = Double.parseDouble(resultadoEsperado);
                double real = Double.parseDouble(textoResultado);
                
                System.out.println("🧮 Comparación numérica:");
                System.out.println("   - Esperado: " + esperado);
                System.out.println("   - Obtenido: " + real);
                System.out.println("   - Diferencia: " + Math.abs(esperado - real));
                
                assertEquals(esperado, real, 0.001, 
                    "❌ RESULTADO INCORRECTO:\n" +
                    "   🔢 Operación: " + ultimoNumero1 + " " + ultimaOperacion + " " + ultimoNumero2 + "\n" +
                    "   ✅ Esperado: " + esperado + "\n" +
                    "   ❌ Obtenido: " + real + "\n" +
                    "   📏 Diferencia: " + Math.abs(esperado - real));
                
            } catch (NumberFormatException e) {
                System.err.println("❌ ERROR: No se pudo convertir a número");
                System.err.println("   - Esperado: '" + resultadoEsperado + "'");
                System.err.println("   - Obtenido: '" + textoResultado + "'");
                tomarScreenshot("💥 Error conversión numérica");
                fail("❌ ERROR DE FORMATO: No se pudo convertir '" + textoResultado + 
                     "' a número para comparar con '" + resultadoEsperado + "'");
            }
            
            tomarScreenshot("✅ Resultado verificado correctamente");
            System.out.println("✅ Resultado correcto verificado");
            
        } catch (Exception e) {
            System.err.println("❌ ERROR GRAVE en validación:");
            System.err.println("   - Mensaje: " + e.getMessage());
            System.err.println("   - Tipo: " + e.getClass().getSimpleName());
            if (e.getCause() != null) {
                System.err.println("   - Causa: " + e.getCause().getMessage());
            }
            tomarScreenshot("💥 Error grave en validación");
            throw new AssertionError("Fallo grave en validación de resultado", e);
        }
    }

    @Step("Verificar resultado aproximado: {resultadoEsperado}")
    @Entonces("el resultado debe ser cerca de {}")
    public void elResultadoDebeSerCercaDe(String resultadoEsperado) {
        System.out.println("\n📍 VALIDACIÓN: Verificando resultado aproximado = " + resultadoEsperado);
        System.out.println("📊 Contexto:");
        System.out.println("   - Número 1: " + ultimoNumero1);
        System.out.println("   - Número 2: " + ultimoNumero2);
        System.out.println("   - Operación: " + ultimaOperacion);
        
        try {
            WebElement resultado = esperarElementoSeguro(By.cssSelector("[data-testid='resultado-valor']"));
            
            esperarSeguro(2000);
            String textoResultado = resultado.getText().trim();
            System.out.println("🔍 Resultado obtenido: '" + textoResultado + "'");
            
            assertFalse(textoResultado.contains("Error"), 
                "❌ ERROR en resultado: " + textoResultado);
            
            double esperado = Double.parseDouble(resultadoEsperado);
            double real = Double.parseDouble(textoResultado);
            double diferencia = Math.abs(esperado - real);
            
            System.out.println("🧮 Comparación aproximada (±0.1):");
            System.out.println("   - Esperado: " + esperado);
            System.out.println("   - Obtenido: " + real);
            System.out.println("   - Diferencia: " + diferencia);
            
            assertEquals(esperado, real, 0.1, 
                "❌ RESULTADO APROXIMADO INCORRECTO:\n" +
                "   🔢 Operación: " + ultimoNumero1 + " " + ultimaOperacion + " " + ultimoNumero2 + "\n" +
                "   ✅ Esperado: " + esperado + " (±0.1)\n" +
                "   ❌ Obtenido: " + real + "\n" +
                "   📏 Diferencia: " + diferencia);
            
            tomarScreenshot("✅ Resultado aproximado verificado");
            System.out.println("✅ Resultado aproximado correcto");
            
        } catch (Exception e) {
            System.err.println("❌ ERROR en validación aproximada: " + e.getMessage());
            tomarScreenshot("💥 Error validación aproximada");
            throw new AssertionError("Fallo en validación aproximada", e);
        }
    }

    @Step("Verificar mensaje de error: {mensajeEsperado}")
    @Entonces("debe mostrar el mensaje de error {string}")
    public void debeMostrarElMensajeDeError(String mensajeEsperado) {
        System.out.println("\n📍 VALIDACIÓN: Verificando mensaje de error = " + mensajeEsperado);
        
        try {
            WebElement resultado = esperarElementoSeguro(By.cssSelector("[data-testid='resultado-valor']"));
            
            esperarSeguro(2000);
            String textoResultado = resultado.getText();
            System.out.println("🔍 Mensaje obtenido: '" + textoResultado + "'");
            
            assertEquals(mensajeEsperado, textoResultado,
                "❌ MENSAJE DE ERROR INCORRECTO:\n" +
                "   ✅ Esperado: '" + mensajeEsperado + "'\n" +
                "   ❌ Obtenido: '" + textoResultado + "'");
            
            tomarScreenshot("✅ Mensaje de error verificado");
            System.out.println("✅ Mensaje de error correcto");
            
        } catch (Exception e) {
            System.err.println("❌ ERROR en validación de mensaje: " + e.getMessage());
            tomarScreenshot("💥 Error validación mensaje");
            throw new AssertionError("Fallo en validación de mensaje de error", e);
        }
    }

    @Step("Verificar campos vacíos")
    @Entonces("los campos de entrada deben estar vacíos")
    public void losCamposDeEntradaDebenEstarVacios() {
        System.out.println("\n📍 VALIDACIÓN: Verificando campos vacíos");
        
        try {
            WebElement numero1 = esperarElementoSeguro(By.cssSelector("[data-testid='numero1']"));
            WebElement numero2 = esperarElementoSeguro(By.cssSelector("[data-testid='numero2']"));
            
            esperarSeguro(1000);
            
            String valor1 = numero1.getAttribute("value");
            String valor2 = numero2.getAttribute("value");
            
            System.out.println("🔍 Valores de campos:");
            System.out.println("   - Campo 1: '" + valor1 + "'");
            System.out.println("   - Campo 2: '" + valor2 + "'");
            
            assertEquals("", valor1, "❌ Campo 1 no está vacío: '" + valor1 + "'");
            assertEquals("", valor2, "❌ Campo 2 no está vacío: '" + valor2 + "'");
            
            tomarScreenshot("✅ Campos verificados como vacíos");
            System.out.println("✅ Campos vacíos verificados");
            
        } catch (Exception e) {
            System.err.println("❌ ERROR en validación de campos vacíos: " + e.getMessage());
            tomarScreenshot("💥 Error validación campos");
            throw new AssertionError("Fallo en validación de campos vacíos", e);
        }
    }

    @Y("el resultado debe mostrar {string}")
    public void elResultadoDebeMostrar(String textoEsperado) {
        System.out.println("\n📍 VALIDACIÓN: Verificando texto resultado = " + textoEsperado);
        
        try {
            WebElement resultado = esperarElementoSeguro(By.cssSelector("[data-testid='resultado-valor']"));
            
            esperarSeguro(1000);
            String textoObtenido = resultado.getText();
            System.out.println("🔍 Texto obtenido: '" + textoObtenido + "'");
            
            assertEquals(textoEsperado, textoObtenido,
                "❌ TEXTO DE RESULTADO INCORRECTO:\n" +
                "   ✅ Esperado: '" + textoEsperado + "'\n" +
                "   ❌ Obtenido: '" + textoObtenido + "'");
            
            tomarScreenshot("✅ Texto resultado verificado");
            System.out.println("✅ Texto resultado correcto");
            
        } catch (Exception e) {
            System.err.println("❌ ERROR en validación de texto resultado: " + e.getMessage());
            throw new AssertionError("Fallo en validación de texto resultado", e);
        }
    }

    @Y("la operación debe mostrar {string}")
    public void laOperacionDebeMostrar(String textoEsperado) {
        System.out.println("\n📍 VALIDACIÓN: Verificando texto operación = " + textoEsperado);
        
        try {
            WebElement operacion = esperarElementoSeguro(By.cssSelector("[data-testid='resultado-operacion']"));
            
            esperarSeguro(1000);
            String textoObtenido = operacion.getText();
            System.out.println("🔍 Texto operación obtenido: '" + textoObtenido + "'");
            
            assertEquals(textoEsperado, textoObtenido,
                "❌ TEXTO DE OPERACIÓN INCORRECTO:\n" +
                "   ✅ Esperado: '" + textoEsperado + "'\n" +
                "   ❌ Obtenido: '" + textoObtenido + "'");
            
            tomarScreenshot("✅ Texto operación verificado");
            System.out.println("✅ Texto operación correcto");
            
        } catch (Exception e) {
            System.err.println("❌ ERROR en validación de texto operación: " + e.getMessage());
            throw new AssertionError("Fallo en validación de texto operación", e);
        }
    }

    // ==========================================
    // MÉTODOS PRIVADOS ULTRA ESTABLES
    // ==========================================

    /**
     * Inicializa WebDriver con configuración ultra estable
     * para evitar Connection Reset y errores de WebSocket
     */
    private void inicializarWebDriverEstable() {
        System.out.println("🔧 Configurando WebDriver ultra estable...");
        
        // Configurar WebDriverManager con versión específica
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        
        boolean headless = Boolean.parseBoolean(System.getProperty("webdriver.headless", "false"));
        System.out.println("👁️ Modo headless: " + headless);
        
        if (headless) {
            options.addArguments("--headless=new"); // Nuevo headless mode más estable
        }
        
        // Configuración ULTRA ESTABLE para evitar Connection Reset
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-web-security");
        options.addArguments("--disable-features=VizDisplayCompositor");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-plugins");
        options.addArguments("--disable-images"); // Cargar más rápido
        options.addArguments("--disable-javascript-harmony-shipping");
        options.addArguments("--disable-background-timer-throttling");
        options.addArguments("--disable-renderer-backgrounding");
        options.addArguments("--disable-backgrounding-occluded-windows");
        options.addArguments("--disable-client-side-phishing-detection");
        options.addArguments("--disable-crashpad");
        options.addArguments("--disable-oopr-debug-crash-dump");
        options.addArguments("--no-crash-upload");
        options.addArguments("--disable-low-res-tiling");
        options.addArguments("--log-level=3"); // Solo errores críticos
        options.addArguments("--silent");
        options.addArguments("--window-size=1200,800");
        
        // Configuraciones de red para evitar timeouts
        options.addArguments("--aggressive-cache-discard");
        options.addArguments("--memory-pressure-off");
        options.addArguments("--max_old_space_size=4096");
        
        // Deshabilitar logging excesivo
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        
        System.out.println("⚙️ Opciones Chrome ultra estables configuradas");
        
        driver = new ChromeDriver(options);
        
        // Timeouts GENEROSOS para evitar Connection Reset
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        longWait = new WebDriverWait(driver, Duration.ofSeconds(60));
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(60));
        
        System.out.println("⏰ Timeouts ultra generosos configurados:");
        System.out.println("   - Wait: 30s");
        System.out.println("   - LongWait: 60s");
        System.out.println("   - Implicit: 20s");
        System.out.println("   - PageLoad: 90s");
        System.out.println("   - Script: 60s");
        
        // Configurar ventana para estabilidad
        try {
            driver.manage().window().setSize(new org.openqa.selenium.Dimension(1200, 800));
        } catch (Exception e) {
            System.out.println("⚠️ No se pudo configurar tamaño de ventana: " + e.getMessage());
        }
    }

    /**
     * Cierra WebDriver de forma ultra segura para evitar Connection Reset
     */
    private void cerrarWebDriverSeguro() {
        if (driver != null) {
            try {
                System.out.println("🔒 Cerrando WebDriver de forma segura...");
                
                // Esperar antes de cerrar
                esperarSeguro(1000);
                
                // Cerrar todas las ventanas gradualmente
                try {
                    for (String windowHandle : driver.getWindowHandles()) {
                        driver.switchTo().window(windowHandle);
                        esperarSeguro(500);
                    }
                } catch (Exception e) {
                    System.out.println("⚠️ Error al cerrar ventanas: " + e.getMessage());
                }
                
                // Quit final
                driver.quit();
                esperarSeguro(500); // Esperar que termine completamente
                
                System.out.println("✅ WebDriver cerrado de forma segura");
                
            } catch (Exception e) {
                System.err.println("⚠️ Error al cerrar WebDriver (pero continuando): " + e.getMessage());
            } finally {
                driver = null;
            }
        }
    }

    /**
     * Navegar con reintentos para evitar errores de carga
     */
    private void navegarConReintentos(String url, int intentos) {
        Exception ultimoError = null;
        
        for (int i = 1; i <= intentos; i++) {
            try {
                System.out.println("🌐 Intento " + i + " de " + intentos + " para navegar a: " + url);
                driver.get(url);
                esperarSeguro(2000); // Esperar que cargue
                return; // Éxito
            } catch (Exception e) {
                ultimoError = e;
                System.err.println("❌ Intento " + i + " falló: " + e.getMessage());
                if (i < intentos) {
                    System.out.println("⏳ Esperando antes del siguiente intento...");
                    esperarSeguro(3000);
                }
            }
        }
        
        throw new RuntimeException("Fallo al navegar después de " + intentos + " intentos", ultimoError);
    }

    /**
     * Esperar elemento con manejo robusto de errores
     */
    private WebElement esperarElementoSeguro(By locator) {
        try {
            return longWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            System.err.println("❌ Error esperando elemento: " + locator);
            System.err.println("   - Error: " + e.getMessage());
            tomarScreenshot("💥 Error esperando elemento");
            throw new RuntimeException("Elemento no encontrado: " + locator, e);
        }
    }

    /**
     * Ingresar número con validación y reintentos
     */
    private void ingresarNumeroSeguro(String campo, String valor) {
        System.out.println("⌨️ Ingresando '" + valor + "' en campo '" + campo + "'");
        
        try {
            WebElement elemento = esperarElementoSeguro(By.cssSelector("[data-testid='" + campo + "']"));
            
            // Esperar que el elemento sea interactuable
            wait.until(ExpectedConditions.elementToBeClickable(elemento));
            
            // Hacer foco en el elemento
            elemento.click();
            esperarSeguro(500);
            
            // Limpiar con múltiples métodos
            elemento.clear();
            esperarSeguro(300);
            
            // Si aún tiene valor, limpiar con Ctrl+A + Delete
            String valorActual = elemento.getAttribute("value");
            if (valorActual != null && !valorActual.isEmpty()) {
                elemento.sendKeys(org.openqa.selenium.Keys.CONTROL + "a");
                esperarSeguro(100);
                elemento.sendKeys(org.openqa.selenium.Keys.DELETE);
                esperarSeguro(300);
            }
            
            // Ingresar el valor nuevo
            elemento.sendKeys(valor);
            esperarSeguro(500);
            
            // Verificar que se ingresó correctamente
            String valorFinal = elemento.getAttribute("value");
            System.out.println("🔍 Verificando: '" + valor + "' vs '" + valorFinal + "'");
            
            if (!valor.equals(valorFinal)) {
                System.err.println("⚠️ ADVERTENCIA: Valor no coincide, reintentando...");
                elemento.clear();
                esperarSeguro(500);
                elemento.sendKeys(valor);
                esperarSeguro(500);
                
                valorFinal = elemento.getAttribute("value");
                if (!valor.equals(valorFinal)) {
                    System.err.println("❌ ERROR: No se pudo ingresar el valor correctamente");
                    System.err.println("   - Esperado: '" + valor + "'");
                    System.err.println("   - Final: '" + valorFinal + "'");
                }
            }
            
            System.out.println("✅ Número '" + valor + "' ingresado en '" + campo + "'");
            
        } catch (Exception e) {
            System.err.println("❌ ERROR al ingresar número:");
            System.err.println("   - Campo: " + campo);
            System.err.println("   - Valor: " + valor);
            System.err.println("   - Error: " + e.getMessage());
            tomarScreenshot("💥 Error ingresando número");
            throw new RuntimeException("Fallo al ingresar número", e);
        }
    }

    /**
     * Hacer clic con esperas y validaciones
     */
    private void hacerClicSeguro(String operacion) {
        String dataTestId = mapearOperacionATestId(operacion);
        System.out.println("🔘 Haciendo clic seguro en botón: " + dataTestId);
        
        try {
            WebElement boton = esperarElementoSeguro(By.cssSelector("[data-testid='" + dataTestId + "']"));
            
            // Esperar que sea clickeable
            wait.until(ExpectedConditions.elementToBeClickable(boton));
            
            // Scroll para asegurar visibilidad
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", boton);
            esperarSeguro(500);
            
            // Resaltar brevemente
            resaltarElementoSeguro(boton);
            
            // Hacer clic con JavaScript como respaldo
            try {
                boton.click();
            } catch (Exception e) {
                System.out.println("⚠️ Clic normal falló, usando JavaScript...");
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", boton);
            }
            
            System.out.println("✅ Clic ejecutado en: " + operacion);
            
            // Esperar que se procese la operación
            esperarSeguro(3000);
            
        } catch (Exception e) {
            System.err.println("❌ ERROR al hacer clic en botón:");
            System.err.println("   - Operación: " + operacion);
            System.err.println("   - TestId: " + dataTestId);
            System.err.println("   - Error: " + e.getMessage());
            tomarScreenshot("💥 Error haciendo clic");
            throw new RuntimeException("Fallo al hacer clic en botón", e);
        }
    }

    private String mapearOperacionATestId(String operacion) {
        switch (operacion.toLowerCase()) {
            case "suma": return "btn-suma";
            case "resta": return "btn-resta";
            case "multiplicacion": return "btn-multiplicacion";
            case "division": return "btn-division";
            case "limpiar": return "btn-limpiar";
            case "igual": return "btn-igual";
            default:
                throw new IllegalArgumentException("Operación no reconocida: " + operacion);
        }
    }

    private void resaltarElementoSeguro(WebElement elemento) {
        try {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].style.border='3px solid red'; " +
                "arguments[0].style.backgroundColor='yellow';", elemento);
            esperarSeguro(300);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].style.border=''; " +
                "arguments[0].style.backgroundColor='';", elemento);
        } catch (Exception e) {
            // Ignorar errores de resaltado
        }
    }

    /**
     * Espera segura sin interrupciones
     */
    private void esperarSeguro(int milisegundos) {
        try {
            TimeUnit.MILLISECONDS.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("⚠️ Espera interrumpida");
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] tomarScreenshot(String descripcion) {
        try {
            if (driver instanceof TakesScreenshot) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                String fileName = String.format("screenshot_%03d_%s_%s.png", 
                    screenshotCounter++, timestamp, descripcion.replaceAll("[^a-zA-Z0-9]", "_"));
                
                File screenshotDir = new File("target/screenshots");
                screenshotDir.mkdirs();
                
                File screenshotFile = new File(screenshotDir, fileName);
                FileUtils.writeByteArrayToFile(screenshotFile, screenshot);
                
                scenario.attach(screenshot, "image/png", descripcion);
                
                System.out.println("📸 Screenshot: " + fileName);
                return screenshot;
            }
        } catch (Exception e) {
            System.err.println("❌ Error al tomar screenshot: " + e.getMessage());
        }
        return new byte[0];
    }
}