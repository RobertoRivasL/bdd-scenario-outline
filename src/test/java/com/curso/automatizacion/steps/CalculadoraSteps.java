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

import static org.junit.jupiter.api.Assertions.*;

/**
 * Step Definitions con WAITS LARGOS y SCREENSHOTS AUTOMÁTICOS
 * Para mejor visualización y documentación de las pruebas
 */
public class CalculadoraSteps {
    private WebDriver driver;
    private WebDriverWait wait;
    private WebDriverWait longWait;
    private Scenario scenario;
    private static int screenshotCounter = 1;

    @Before
    public void setUp(Scenario scenario) {
        this.scenario = scenario;
        
        // Configuración WebDriver con timeouts largos
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        
        // Para ver la calculadora funcionando (NO headless por defecto)
        boolean headless = Boolean.parseBoolean(System.getProperty("webdriver.headless", "false"));
        if (headless) {
            options.addArguments("--headless");
        }
        
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1200,800");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-web-security");
        
        driver = new ChromeDriver(options);
        
        // Waits largos para visualización
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        longWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        // Timeouts implícitos generosos
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        
        System.out.println("🚀 Configuración WebDriver completada - Waits largos activados");
        tomarScreenshotInicioEscenario();
    }

    @After
    public void tearDown() {
        if (scenario.isFailed()) {
            tomarScreenshotEnFallo();
        } else {
            tomarScreenshotFinalExitoso();
        }
        
        // Esperar un poco antes de cerrar para ver el resultado final
        esperarParaVisualizacion(2000);
        
        if (driver != null) {
            driver.quit();
        }
        
        System.out.println("✅ Scenario completado: " + scenario.getName());
    }

    // ===== STEPS CON SCREENSHOTS AUTOMÁTICOS =====

    @Step("Navegar a la página de la calculadora")
    @Dado("que estoy en la página de la calculadora")
    public void queEstoyEnLaPaginaDeLaCalculadora() {
        String rutaAplicacion = "file://" + System.getProperty("user.dir") + "/src/test/resources/calculadora.html";
        
        System.out.println("🌐 Navegando a: " + rutaAplicacion);
        driver.get(rutaAplicacion);
        
        // Esperar a que la página cargue completamente
        WebElement titulo = longWait.until(
            ExpectedConditions.presenceOfElementLocated(By.tagName("h1"))
        );
        
        assertTrue(titulo.getText().contains("Calculadora"), 
            "La página no se cargó correctamente - título: " + titulo.getText());
        
        // Screenshot después de cargar
        tomarScreenshot("Página de calculadora cargada");
        
        // Esperar un poco para visualización
        esperarParaVisualizacion(1500);
        
        System.out.println("✅ Página de calculadora cargada exitosamente");
    }

    @Step("Realizar operación previa")
    @Dado("que he realizado una operación")
    public void queHeRealizadoUnaOperacion() {
        System.out.println("🔄 Realizando operación previa para setup...");
        
        ingresarNumeroConVisualizacion("numero1", "10");
        ingresarNumeroConVisualizacion("numero2", "5");
        hacerClicEnBotonConVisualizacion("suma");
        
        tomarScreenshot("Operación previa completada");
        System.out.println("✅ Operación previa completada");
    }

    @Step("Ingresar primer número: {numero}")
    @Cuando("ingreso el primer número {}")
    public void ingresoElPrimerNumero(String numero) {
        System.out.println("🔢 Ingresando primer número: " + numero);
        ingresarNumeroConVisualizacion("numero1", numero);
        tomarScreenshot("Primer número ingresado: " + numero);
    }

    @Step("Ingresar segundo número: {numero}")
    @Y("ingreso el segundo número {}")
    public void ingresoElSegundoNumero(String numero) {
        System.out.println("🔢 Ingresando segundo número: " + numero);
        ingresarNumeroConVisualizacion("numero2", numero);
        tomarScreenshot("Segundo número ingresado: " + numero);
    }

    @Step("Hacer clic en operación: {operacion}")
    @Y("hago clic en el botón de {word}")
    public void hagoClicEnElBotonDe(String operacion) {
        System.out.println("➕ Ejecutando operación: " + operacion);
        hacerClicEnBotonConVisualizacion(operacion);
        tomarScreenshot("Operación ejecutada: " + operacion);
    }

    @Step("Hacer clic en botón: {nombreBoton}")
    @Cuando("hago clic en el botón {string}")
    public void hagoClicEnElBoton(String nombreBoton) {
        System.out.println("🔘 Haciendo clic en botón: " + nombreBoton);
        
        switch (nombreBoton.toLowerCase()) {
            case "limpiar":
                hacerClicEnBotonConVisualizacion("limpiar");
                break;
            case "=":
            case "igual":
                hacerClicEnBotonConVisualizacion("igual");
                break;
            default:
                throw new IllegalArgumentException("Botón no reconocido: " + nombreBoton);
        }
        
        tomarScreenshot("Botón presionado: " + nombreBoton);
    }

    // ===== VALIDACIONES CON SCREENSHOTS =====

    @Step("Verificar resultado exacto: {resultadoEsperado}")
    @Entonces("el resultado debe ser exactamente {}")
    public void elResultadoDebeSerExactamente(String resultadoEsperado) {
        System.out.println("✔️ Verificando resultado exacto: " + resultadoEsperado);
        
        // Esperar a que aparezca el resultado
        WebElement resultado = longWait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("[data-testid='resultado-valor']")
            )
        );
        
        // Esperar un poco más para que se actualice el valor
        esperarParaVisualizacion(1000);
        
        String textoResultado = resultado.getText().trim();
        System.out.println("🔍 Resultado obtenido: '" + textoResultado + "'");
        
        assertFalse(textoResultado.contains("Error"), 
            "No debería haber error - Resultado: " + textoResultado);
        
        double esperado = Double.parseDouble(resultadoEsperado);
        double real = Double.parseDouble(textoResultado);
        
        assertEquals(esperado, real, 0.001, 
            "Resultado exacto - Esperado: " + esperado + ", Obtenido: " + real);
        
        tomarScreenshot("Resultado verificado: " + textoResultado);
        System.out.println("✅ Resultado correcto verificado");
    }

    @Step("Verificar resultado aproximado: {resultadoEsperado}")
    @Entonces("el resultado debe ser cerca de {}")
    public void elResultadoDebeSerCercaDe(String resultadoEsperado) {
        System.out.println("≈ Verificando resultado aproximado: " + resultadoEsperado);
        
        WebElement resultado = longWait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("[data-testid='resultado-valor']")
            )
        );
        
        esperarParaVisualizacion(1000);
        
        String textoResultado = resultado.getText().trim();
        System.out.println("🔍 Resultado obtenido: '" + textoResultado + "'");
        
        assertFalse(textoResultado.contains("Error"), 
            "No debería haber error - Resultado: " + textoResultado);
        
        double esperado = Double.parseDouble(resultadoEsperado);
        double real = Double.parseDouble(textoResultado);
        
        assertEquals(esperado, real, 0.1, 
            "Resultado aproximado - Esperado: " + esperado + " (±0.1), Obtenido: " + real);
        
        tomarScreenshot("Resultado aproximado verificado: " + textoResultado);
        System.out.println("✅ Resultado aproximado correcto");
    }

    @Step("Verificar mensaje de error: {mensajeEsperado}")
    @Entonces("debe mostrar el mensaje de error {string}")
    public void debeMostrarElMensajeDeError(String mensajeEsperado) {
        System.out.println("⚠️ Verificando mensaje de error: " + mensajeEsperado);
        
        WebElement resultado = longWait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("[data-testid='resultado-valor']")
            )
        );
        
        esperarParaVisualizacion(1000);
        
        String textoResultado = resultado.getText();
        System.out.println("🔍 Mensaje obtenido: '" + textoResultado + "'");
        
        assertEquals(mensajeEsperado, textoResultado,
            "Mensaje de error - Esperado: '" + mensajeEsperado + "', Obtenido: '" + textoResultado + "'");
        
        tomarScreenshot("Mensaje de error verificado: " + textoResultado);
        System.out.println("✅ Mensaje de error correcto");
    }

    @Step("Verificar campos vacíos")
    @Entonces("los campos de entrada deben estar vacíos")
    public void losCamposDeEntradaDebenEstarVacios() {
        System.out.println("🔍 Verificando que los campos estén vacíos...");
        
        WebElement numero1 = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='numero1']"))
        );
        WebElement numero2 = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='numero2']"))
        );
        
        esperarParaVisualizacion(1000);
        
        String valor1 = numero1.getAttribute("value");
        String valor2 = numero2.getAttribute("value");
        
        System.out.println("🔍 Valor campo 1: '" + valor1 + "'");
        System.out.println("🔍 Valor campo 2: '" + valor2 + "'");
        
        assertEquals("", valor1, "El primer número debería estar vacío");
        assertEquals("", valor2, "El segundo número debería estar vacío");
        
        tomarScreenshot("Campos verificados como vacíos");
        System.out.println("✅ Campos vacíos verificados");
    }

    @Y("el resultado debe mostrar {string}")
    public void elResultadoDebeMostrar(String textoEsperado) {
        System.out.println("🔍 Verificando texto del resultado: " + textoEsperado);
        
        WebElement resultado = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("[data-testid='resultado-valor']")
            )
        );
        
        esperarParaVisualizacion(1000);
        String textoObtenido = resultado.getText();
        
        assertEquals(textoEsperado, textoObtenido,
            "Texto del resultado - Esperado: '" + textoEsperado + "', Obtenido: '" + textoObtenido + "'");
        
        tomarScreenshot("Texto del resultado verificado");
    }

    @Y("la operación debe mostrar {string}")
    public void laOperacionDebeMostrar(String textoEsperado) {
        System.out.println("🔍 Verificando texto de la operación: " + textoEsperado);
        
        WebElement operacion = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("[data-testid='resultado-operacion']")
            )
        );
        
        esperarParaVisualizacion(1000);
        String textoObtenido = operacion.getText();
        
        assertEquals(textoEsperado, textoObtenido,
            "Texto de la operación - Esperado: '" + textoEsperado + "', Obtenido: '" + textoObtenido + "'");
        
        tomarScreenshot("Texto de operación verificado");
    }

    // ===== MÉTODOS AUXILIARES CON VISUALIZACIÓN =====

    private void ingresarNumeroConVisualizacion(String campo, String valor) {
        System.out.println("⌨️ Ingresando '" + valor + "' en campo '" + campo + "'");
        
        WebElement elemento = longWait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("[data-testid='" + campo + "']")
            )
        );
        
        // Hacer clic primero para enfocar
        elemento.click();
        esperarParaVisualizacion(500);
        
        // Limpiar y escribir con pausa para visualización
        elemento.clear();
        esperarParaVisualizacion(300);
        
        // Escribir carácter por carácter para mejor visualización
        for (char c : valor.toCharArray()) {
            elemento.sendKeys(String.valueOf(c));
            esperarParaVisualizacion(200);
        }
        
        System.out.println("✅ Número '" + valor + "' ingresado en '" + campo + "'");
    }

    private void hacerClicEnBotonConVisualizacion(String operacion) {
        String dataTestId = mapearOperacionATestId(operacion);
        System.out.println("🔘 Haciendo clic en botón: " + dataTestId);
        
        WebElement boton = longWait.until(
            ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-testid='" + dataTestId + "']")
            )
        );
        
        // Resaltar el botón antes de hacer clic
        resaltarElemento(boton);
        
        boton.click();
        
        // Esperar para que se procese la operación y sea visible
        esperarParaVisualizacion(1500);
        
        System.out.println("✅ Clic ejecutado en: " + operacion);
    }

    private void resaltarElemento(WebElement elemento) {
        try {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].style.border='3px solid red'; " +
                "arguments[0].style.backgroundColor='yellow';", elemento);
            esperarParaVisualizacion(500);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].style.border=''; " +
                "arguments[0].style.backgroundColor='';", elemento);
        } catch (Exception e) {
            System.out.println("⚠️ No se pudo resaltar elemento: " + e.getMessage());
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

    private void esperarParaVisualizacion(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // ===== MÉTODOS DE SCREENSHOTS =====

    private void tomarScreenshotInicioEscenario() {
        tomarScreenshot("Inicio del escenario: " + scenario.getName());
    }

    private void tomarScreenshotFinalExitoso() {
        tomarScreenshot("Escenario completado exitosamente");
    }

    private void tomarScreenshotEnFallo() {
        tomarScreenshot("FALLO en escenario");
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] tomarScreenshot(String descripcion) {
        try {
            if (driver instanceof TakesScreenshot) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                
                // También guardar en disco para Cucumber HTML
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                String fileName = String.format("screenshot_%03d_%s_%s.png", 
                    screenshotCounter++, timestamp, descripcion.replaceAll("[^a-zA-Z0-9]", "_"));
                
                File screenshotDir = new File("target/screenshots");
                screenshotDir.mkdirs();
                
                File screenshotFile = new File(screenshotDir, fileName);
                FileUtils.writeByteArrayToFile(screenshotFile, screenshot);
                
                // Adjuntar a Cucumber scenario
                scenario.attach(screenshot, "image/png", descripcion);
                
                System.out.println("📸 Screenshot guardado: " + fileName);
                return screenshot;
            }
        } catch (IOException e) {
            System.err.println("❌ Error al tomar screenshot: " + e.getMessage());
        }
        return new byte[0];
    }
}
