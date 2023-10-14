package es.androidp.myapplication2conprofe

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT  //Para bloquear la pantalla que no se gire.

        val operandoA: EditText = findViewById(R.id.txtOperandoA)
        val operandoB: EditText = findViewById(R.id.txtOperandoB)
        val resultado: TextView = findViewById(R.id.txtResultado)

        val buttonSumar: Button = findViewById(R.id.botonSumar)
        val buttonRestar: Button = findViewById(R.id.botonRestar)
        val buttonMultiplicar: Button = findViewById(R.id.botonMultiplicar)
        val buttonDividir: Button = findViewById(R.id.botonDividir)

        // Función para ocultar el teclado
        fun hideKeyboard(view: EditText) {
            val inputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }

        // Función para realizar una operación
        fun realizarOperacion(operacion: String) {
            val opA: Double = operandoA.text.toString().toDouble()
            val opB: Double = operandoB.text.toString().toDouble()

            val resultadoOperacion = when (operacion) {
                "Sumar" -> opA + opB
                "Restar" -> opA - opB
                "Multiplicar" -> opA * opB
                "Dividir" -> {
                    if (opB != 0.0) {
                        opA / opB
                    } else {
                        // Manejo de división por cero
                        0.0
                    }
                }
                else -> 0.0
            }

            resultado.text = resultadoOperacion.toString()
            hideKeyboard(operandoA)
            hideKeyboard(operandoB)
        }

        // Configurar escuchadores para los botones
        buttonSumar.setOnClickListener { realizarOperacion("Sumar") }
        buttonRestar.setOnClickListener { realizarOperacion("Restar") }
        buttonMultiplicar.setOnClickListener { realizarOperacion("Multiplicar") }
        buttonDividir.setOnClickListener { realizarOperacion("Dividir") }

        // Configurar el comportamiento de los campos de entrada
        operandoA.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                operandoA.text.clear()
            } else if (operandoA.text.isBlank()) {
                operandoA.setText("Cajas")
            }
        }

        operandoB.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                operandoB.text.clear()
            } else if (operandoB.text.isBlank()) {
                operandoB.setText("Metros")
            }
        }
    }
}

