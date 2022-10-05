package com.example.testapp1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.testapp1.databinding.ActivityQuadraticCalculatorBinding
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt

class QuadraticCalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuadraticCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        var lastToast = Toast(applicationContext)
        var cancelNext = false
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quadratic_calculator)
        binding = ActivityQuadraticCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateBtn.setOnClickListener{
            val a = binding.quadraticA.text?.toString()?.toDoubleOrNull()
            val b = binding.quadraticB.text?.toString()?.toDoubleOrNull()
            val c = binding.quadraticC.text?.toString()?.toDoubleOrNull()
            if(a != null && b != null && c!= null) {
                val quadraticResults = quadraticFunc(a, b, c)
                val text: String = if (quadraticResults.size == 1) {
                    "x: ${quadraticResults[0]}"
                } else {
                    "x1: ${quadraticResults[0]}\nx2: ${quadraticResults[1]}"
                }
                if(cancelNext) lastToast.cancel()
                lastToast = Toast.makeText(applicationContext, text, Toast.LENGTH_LONG)
                lastToast.show()
                cancelNext = true
            }
        }

        binding.mainMenuBtn.setOnClickListener{
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }
    }

    private fun quadraticFunc(a: Double, b: Double, c: Double): Array<String> {
        val delta = b * b - 4 * a * c
        return if (delta > 0) {
            val x1 = (-b + sqrt(delta)) / (2 * a)
            val x2 = (-b - sqrt(delta)) / (2 * a)
            arrayOf(x1.toString(), x2.toString())
        } else if (delta == 0.0){
            val x = (-b) / (2 * a)
            arrayOf(x.toString())
        } else {
            val realPart = round(-b / (2 * a), 3)
            val imaginaryPart = round(sqrt(abs(delta)) / (2 * a), 3)
            val x1 = "$realPart + $imaginaryPart i"
            val x2 = "$realPart - $imaginaryPart i"
            arrayOf(x1, x2)
        }
    }

    private fun round(number: Double, decimalPlaces: Int): Double {
        val margin = 10.0.pow(decimalPlaces)
        return (number * margin).roundToInt() / margin
    }
}