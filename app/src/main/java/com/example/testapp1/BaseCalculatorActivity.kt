package com.example.testapp1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testapp1.databinding.ActivityBaseCalculatorBinding

class BaseCalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBaseCalculatorBinding
    private val maxCharacters = 9

    override fun onCreate(savedInstanceState: Bundle?) {
        var firstNumber: Double? = null
        var secondNumber: Double?
        var sign: Operation? = null
        var calculated = false

        fun handleCharacterInput(character: String){
            val text = if(getDisplayText() == "0" && character != ".") character else getDisplayText() + character
            if(text.length <= maxCharacters && !calculated)
                binding.calcDisplay.text = text
        }

        fun handleOperationSign(character: String){
            val wasNull = sign == null
            sign = Operation.toOperation(character)
            if(wasNull || calculated) {
                firstNumber = getDisplayText().toDouble()
                binding.calcDisplay.text = "0"
                if(calculated) calculated = false
            }
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_calculator)
        binding = ActivityBaseCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calcHelloBtn.setOnClickListener{
            //I don't know what to put here
        }

        binding.calcCBtn.setOnClickListener {
            firstNumber = null
            secondNumber = null
            sign = null
            calculated = false
            binding.calcDisplay.text = "0"
        }

        binding.calcAdditionBtn.setOnClickListener {
            handleOperationSign("+")
        }

        binding.calcSubtractionBtn.setOnClickListener {
            handleOperationSign("-")
        }

        binding.calcMultiplicationBtn.setOnClickListener {
            handleOperationSign("*")
        }

        binding.calcDivisionBtn.setOnClickListener {
            handleOperationSign("/")
        }

        binding.calcEqualsBtn.setOnClickListener {
            val res: Double?
            if(firstNumber==null){
                res = getDisplayText().toDouble()
            } else {
                secondNumber = getDisplayText().toDouble()
                res = when (sign.toString()) {
                    "+" -> firstNumber!! + secondNumber!!
                    "-" -> firstNumber!! - secondNumber!!
                    "x" -> firstNumber!! * secondNumber!!
                    "/" -> firstNumber!! / secondNumber!!
                    else -> null
                }
            }

            binding.calcDisplay.text = if (res == null){
                "ERROR"
            } else if (res.toDouble().compareTo(res.toInt()) == 0){
                res.toInt().toString()
            } else {
                res.toString()
            }
            calculated = true
        }

        binding.calc0Btn.setOnClickListener {
            handleCharacterInput("0")
        }

        binding.calc1Btn.setOnClickListener {
            handleCharacterInput("1")
        }

        binding.calc2Btn.setOnClickListener {
            handleCharacterInput("2")
        }

        binding.calc3Btn.setOnClickListener {
            handleCharacterInput("3")
        }

        binding.calc4Btn.setOnClickListener {
            handleCharacterInput("4")
        }

        binding.calc5Btn.setOnClickListener {
            handleCharacterInput("5")
        }

        binding.calc6Btn.setOnClickListener {
            handleCharacterInput("6")
        }

        binding.calc7Btn.setOnClickListener {
            handleCharacterInput("7")
        }

        binding.calc8Btn.setOnClickListener {
            handleCharacterInput("8")
        }

        binding.calc9Btn.setOnClickListener {
            handleCharacterInput("9")
        }

        binding.calcPointBtn.setOnClickListener {
            handleCharacterInput(".")
        }

        binding.mainMenuBtn.setOnClickListener{
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }

    }



    fun getDisplayText(): String{
        return binding.calcDisplay.text.toString()
    }

    enum class Operation{
        ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION;

        override fun toString(): String {
            return when (this.name) {
                "ADDITION" -> "+"
                "SUBTRACTION" -> "-"
                "MULTIPLICATION" -> "x"
                "DIVISION" -> "/"
                else -> ""
            }
        }

        companion object {
            fun toOperation(str: String): Operation? {
                return when (str) {
                    "+" -> ADDITION
                    "-" -> SUBTRACTION
                    "*" -> MULTIPLICATION
                    "/" -> DIVISION
                    else -> {
                        null
                    }
                }
            }
        }

    }
}