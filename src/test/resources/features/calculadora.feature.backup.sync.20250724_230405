#language: es
@calculadora
Característica: Funcionalidad de Calculadora
  Como estudiante de automatización de pruebas
  Quiero probar diferentes operaciones matemáticas
  Para verificar que la calculadora funciona correctamente

  Antecedentes:
    Dado que estoy en la página de la calculadora

  @operaciones-basicas
  Esquema del escenario: Realizar operaciones matemáticas básicas
    Cuando ingreso el primer número <numero1>
    Y ingreso el segundo número <numero2>
    Y hago clic en el botón de <operacion>
    Entonces el resultado debe ser exactamente <resultado_esperado>

    Ejemplos:
      | numero1 | numero2 | operacion      | resultado_esperado |
      | 10      | 5       | suma           | 15                 |
      | 20      | 8       | resta          | 12                 |
      | 7       | 6       | multiplicacion | 42                 |
      | 15      | 3       | division       | 5                  |
      | 100     | 25      | suma           | 125                |
      | 50      | 10      | multiplicacion | 500                |

  @numeros-decimales
  Esquema del escenario: Operaciones con números decimales
    Cuando ingreso el primer número <numero1>
    Y ingreso el segundo número <numero2>
    Y hago clic en el botón de <operacion>
    Entonces el resultado debe ser cerca de <resultado_esperado>

    Ejemplos:
      | numero1 | numero2 | operacion      | resultado_esperado |
      | 2.5     | 1.5     | suma           | 4                  |
      | 10.75   | 2.25    | resta          | 8.5                |
      | 3.14    | 2       | multiplicacion | 6.28               |
      | 22.5    | 4.5     | division       | 5                  |

  @casos-especiales
  Esquema del escenario: Validar casos especiales
    Cuando ingreso el primer número <numero1>
    Y ingreso el segundo número <numero2>
    Y hago clic en el botón de <operacion>
    Entonces debe mostrar el mensaje de error "<mensaje_error>"

    Ejemplos:
      | numero1 | numero2 | operacion | mensaje_error                |
      | 10      | 0       | division  | No se puede dividir por cero |

  @funcionalidad-limpiar
  Escenario: Validar funcionalidad de limpiar
    Dado que he realizado una operación
    Cuando hago clic en el botón "Limpiar"
    Entonces los campos de entrada deben estar vacíos
    Y el resultado debe mostrar "---"
    Y la operación debe mostrar "Selecciona una operación"
