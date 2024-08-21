package com.example.calcxml

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var valAnt = 0.0
    private var valAct = 0.0
    private var operador = ""
    private lateinit var resultado: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultado = findViewById(R.id.editTextText) // Asegúrate de que este ID es el correcto para tu EditText

        // Configura el padding para la vista principal
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        buttonSetup()
    }

    private fun buttonSetup() {
        val listaB = arrayOf(
            R.id.btn9, R.id.btn8, R.id.btn7, R.id.btn6,
            R.id.btn5, R.id.btn4, R.id.btn3, R.id.btn2,
            R.id.btn1, R.id.btnmas, R.id.btnmult,
            R.id.btndiv, R.id.btnmult, R.id.btnhigual
        )

        for (button in listaB) {
            val butonx = findViewById<Button>(button)
            butonx.setOnClickListener { onClickListener(butonx) }
        }
    }

    private fun onClickListener(view: View) {
        when (view.id) {
            R.id.btn9, R.id.btn8, R.id.btn7, R.id.btn6,
            R.id.btn5, R.id.btn4, R.id.btn3, R.id.btn2, R.id.btn1 -> {
                val butonx = findViewById<Button>(view.id)
                appendNum(butonx.text.toString())
            }
            R.id.btnmas, R.id.btnmult, R.id.btndiv -> {
                operador = (view as Button).text.toString()
                valAnt = resultado.text.toString().toDoubleOrNull() ?: 0.0
                resultado.text.clear()
            }
            R.id.btnhigual -> {
                valAct = resultado.text.toString().toDoubleOrNull() ?: 0.0
                val result = performOperation(valAnt, valAct, operador)
                resultado.setText(result.toString())
            }

        }
    }

    private fun appendNum(valor: String) {
        resultado.append(valor)
    }

    private fun performOperation(valAnt: Double, valAct: Double, operador: String): Double {
        return when (operador) {
            "+" -> valAnt + valAct
            "-" -> valAnt - valAct
            "*" -> valAnt * valAct
            "/" -> if (valAct != 0.0) valAnt / valAct else 0.0
            else -> 0.0
        }
    }
}
