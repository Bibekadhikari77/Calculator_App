package com.example.calculator_app

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator_app.R

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private var currentInput: StringBuilder = StringBuilder()
    private var firstOperand: Double = 0.0
    private var operator: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)

        // Number buttons
        val button0: Button = findViewById(R.id.button0)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)
        val buttonDot: Button = findViewById(R.id.buttonDot)

        // Operator buttons
        val buttonPlus: Button = findViewById(R.id.buttonPlus)
        val buttonMinus: Button = findViewById(R.id.buttonMinus)
        val buttonMultiply: Button = findViewById(R.id.buttonMultiply)
        val buttonDivide: Button = findViewById(R.id.buttonDivide)

        // Control buttons
        val buttonAC: Button = findViewById(R.id.buttonAC)
        val buttonEquals: Button = findViewById(R.id.buttonEquals)

        // Set click listeners for number buttons
        val numberButtons = arrayOf(
            button0, button1, button2, button3, button4, button5, button6, button7, button8, button9
        )
        for (button in numberButtons) {
            button.setOnClickListener { appendNumber(button.text.toString()) }
        }
        buttonDot.setOnClickListener { appendDot() }

        // Set click listeners for operator buttons
        buttonPlus.setOnClickListener { setOperator("+") }
        buttonMinus.setOnClickListener { setOperator("-") }
        buttonMultiply.setOnClickListener { setOperator("×") }
        buttonDivide.setOnClickListener { setOperator("÷") }

        // Set click listeners for control buttons
        buttonAC.setOnClickListener { clearAll() }
        buttonEquals.setOnClickListener { calculateResult() }
    }

    private fun appendNumber(number: String) {
        currentInput.append(number)
        resultTextView.text = currentInput.toString()
    }

    private fun appendDot() {
        if (!currentInput.contains(".")) {
            currentInput.append(".")
            resultTextView.text = currentInput.toString()
        }
    }

    private fun setOperator(op: String) {
        if (currentInput.isNotEmpty()) {
            firstOperand = currentInput.toString().toDouble()
            operator = op
            currentInput.clear()
        }
    }

    private fun calculateResult() {
        if (currentInput.isNotEmpty() && operator != null) {
            val secondOperand = currentInput.toString().toDouble()
            var result = 0.0

            when (operator) {
                "+" -> result = firstOperand + secondOperand
                "-" -> result = firstOperand - secondOperand
                "×" -> result = firstOperand * secondOperand
                "÷" -> {
                    if (secondOperand != 0.0) {
                        result = firstOperand / secondOperand
                    } else {
                        resultTextView.text = "Error"
                        clearAll()
                        return
                    }
                }
            }

            resultTextView.text = result.toString()
            currentInput.clear()
            firstOperand = result
            operator = null
        }
    }

    private fun clearAll() {
        currentInput.clear()
        firstOperand = 0.0
        operator = null
        resultTextView.text = "0"
    }
}