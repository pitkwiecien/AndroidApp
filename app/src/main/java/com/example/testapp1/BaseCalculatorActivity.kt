package com.example.testapp1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testapp1.databinding.ActivityBaseCalculatorBinding


class BaseCalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBaseCalculatorBinding
    private val maxCharacters = 9
    private val scNotationCharacters = 4
    private val ignoredDisplays = setOf("0", "HELLO!")

    override fun onCreate(savedInstanceState: Bundle?) {
        var firstNumber: Double? = null
        var secondNumber: Double?
        var sign: Operation? = null
        var calculated = false
        var doNotCalc = false

        fun resetCalculator(){
            firstNumber = null
            secondNumber = null
            sign = null
            calculated = false
            doNotCalc = false
            setDisplayText("0")
        }

        fun handleCharacterInput(character: String){
            if(doNotCalc) return
            val text =
                if (ignoredDisplays.contains(getDisplayText()))
                    if (character == ".")
                        "0."
                    else
                        character
                else
                    getDisplayText() + character
            if(text.length <= maxCharacters && !calculated)
                setDisplayText(text)
        }

        fun handleOperationSign(character: String){
            if(doNotCalc) return
            val wasNull = sign == null
            sign = Operation.toOperation(character)
            if(wasNull || calculated) {
                firstNumber = getDisplayText().toDouble()
                setDisplayText("0")
                if(calculated) calculated = false
            }
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_calculator)
        binding = ActivityBaseCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calcHelloBtn.setOnClickListener{
            setDisplayText("HELLO!")
            doNotCalc = true
        }

        binding.calcCBtn.setOnClickListener {
            resetCalculator()
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
            if(doNotCalc) return@setOnClickListener
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

            var resText =
                if (res == null)
                    "ERROR"
                else if (res.toDouble().compareTo(res.toLong()) == 0)
                    res.toLong().toString()
                else
                    res.toString()

            if(resText.length > maxCharacters) {
                var ePassed = false
                var mainNumberText = ""
                var decPlacesText = ""

                for(elem in resText.toDouble().toString()){
                    if(elem == 'E')
                        ePassed = true
                    else{
                        if(!ePassed)
                            mainNumberText += elem
                        else
                            decPlacesText += elem
                    }
                }
                val mainNumberDbl = QuadraticCalculatorActivity.round(mainNumberText.toDouble(), maxCharacters - scNotationCharacters - 2)
                resText = "${mainNumberDbl}E+${decPlacesText}"
            }
            setDisplayText(resText)
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



    private fun getDisplayText(): String{
        return binding.calcDisplay.text.toString()
    }

    private fun setDisplayText(str: String){
        binding.calcDisplay.text = str
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