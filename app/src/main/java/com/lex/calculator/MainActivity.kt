package com.lex.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import javax.script.ScriptException

class MainActivity : AppCompatActivity() {

    lateinit var etInput: EditText
    lateinit var etResult: EditText

    private var check=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn0 = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btn0)
        val btn00 = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btn00)
        val btn1 = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btn1)
        val btn2 = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btn2)
        val btn3 = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btn3)
        val btn4 = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btn4)
        val btn5 = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btn5)
        val btn6 = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btn6)
        val btn7 = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btn7)
        val btn8 = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btn8)
        val btn9 = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btn9)
        val btnDot = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnDot)
        val btnEqual = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnEqual)
        val btnAdd = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnAdd)
        val btnMinus = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnMinus)
        val btnMultiply = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnMultiply)
        val btnDivide = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnDivide)
        val btnBackSpace = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnBackSpace)
        val btnPercentage = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnPercentage)
        val btnC = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnC)

        try {
            etResult = findViewById(R.id.etResult)
            etInput = findViewById(R.id.etInput)
        }catch (ignored : NullPointerException){

        }

        var text: String

        try {
            btn0.setOnClickListener {
                if (etInput.text.toString() != "") {
                    text = etInput.text.toString() + "0"
                    etInput.setText(text)
                    result(text)
                }
            }

            btn00.setOnClickListener {
                if (etInput.text.toString() != "") {
                    text = etInput.text.toString() + "00"
                    etInput.setText(text)
                    result(text)
                }
            }

            btn1.setOnClickListener {
                text = etInput.text.toString() + "1"
                etInput.setText(text)
                result(text)
            }

            btn2.setOnClickListener {
                text = etInput.text.toString() + "2"
                etInput.setText(text)
                result(text)
            }

            btn3.setOnClickListener {
                text = etInput.text.toString() + "3"
                etInput.setText(text)
                result(text)
            }

            btn4.setOnClickListener {
                text = etInput.text.toString() + "4"
                etInput.setText(text)
                result(text)
            }

            btn5.setOnClickListener {
                text = etInput.text.toString() + "5"
                etInput.setText(text)
                result(text)
            }

            btn6.setOnClickListener {
                text = etInput.text.toString() + "6"
                etInput.setText(text)
                result(text)
            }

            btn7.setOnClickListener {
                text = etInput.text.toString() + "7"
                etInput.setText(text)
                result(text)
            }

            btn8.setOnClickListener {
                text = etInput.text.toString() + "8"
                etInput.setText(text)
                result(text)
            }

            btn9.setOnClickListener {
                text = etInput.text.toString() + "9"
                etInput.setText(text)
                result(text)
            }

            btnDot.setOnClickListener {
                if (etInput.text.toString() != "") {
                    text = etInput.text.toString() + "."
                    etInput.setText(text)
                    result(text)
                }
            }

            btnC.setOnClickListener {
                etInput.text = null
                etResult.text = null
            }

            btnAdd.setOnClickListener {
                if (etInput.text.toString() != "") {
                    text = etInput.text.toString() + "+"
                    etInput.setText(text)
                    check = +1
                }
            }

            btnMinus.setOnClickListener {
                if (etInput.text.toString() != "") {
                    text = etInput.text.toString() + "-"
                    etInput.setText(text)
                    check = +1
                }
            }

            btnMultiply.setOnClickListener {
                if (etInput.text.toString() != "") {
                    text = etInput.text.toString() + "*"
                    etInput.setText(text)
                    check = +1
                }
            }

            btnDivide.setOnClickListener {
                if (etInput.text.toString() != "") {
                text = etInput.text.toString() + "/"
                etInput.setText(text)
                check = +1
                }
            }

            btnPercentage.setOnClickListener {
                if (etInput.text.toString() != "") {
                    text = etInput.text.toString() + "%"
                    etInput.setText(text)
                    check = +1
                }
            }

            btnEqual.setOnClickListener {
                text = etResult.text.toString()
                etInput.setText(text)
                etResult.text = null
            }

            btnBackSpace.setOnClickListener {
                val backspace: String?
                val checktext = etInput.text.toString()
                if (checktext != "") {
                    val stringBuilder: StringBuilder = StringBuilder(etInput.text)
                    val find = etInput.text.toString()
                    val find2 = find.last()

                    if (find2 == '+' || find2 == '-' || find2 == '*' || find2 == '/' || find2 == '%') {
                        check = -1
                    }

                    stringBuilder.deleteCharAt(etInput.text.length - 1)
                    backspace = stringBuilder.toString()
                    etInput.setText(backspace)
                    result(backspace)
                }
                else{
                    etInput.text = null
                    etResult.text = null
                }
            }
        }catch (e : Exception){
            Log.d("TAG", "ERROR")
        }
    }

    private fun result(text: String) {

        val engine: ScriptEngine = ScriptEngineManager().getEngineByName("rhino")

        try {
            val result: Any=engine.eval(text)
            val mainResult=result.toString()
            if (check == 0){
                etResult.setText("0")
            }
            else
            {
                etResult.setText(mainResult)
            }
        }
        catch (e: Exception){
            Log.d("TAG", "ERROR")
        }
    }
}