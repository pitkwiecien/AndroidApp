package com.example.testapp1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.testapp1.databinding.ActivityClickerBinding


class ClickerActivity : AppCompatActivity(){
    private lateinit var binding: ActivityClickerBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        var counter = 0
        var step = 1
        var cancelNext = false
        var lastToast = Toast(applicationContext)
        super.onCreate(savedInstanceState)
        binding = ActivityClickerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.incrementBtn.setOnClickListener {
            counter += step
            binding.counterValue.text = counter.toString()
            val text = (counter - step).toString() + " + " + step + " = " + counter
            if(cancelNext) lastToast.cancel()
            lastToast = Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT)
            lastToast.show()
            cancelNext = true
        }

        binding.decrementBtn.setOnClickListener {
            counter -= step
            binding.counterValue.text = counter.toString()
            val text = (counter - step).toString() + " - " + step + " = " + counter
            if(cancelNext)
                lastToast.cancel()
            lastToast = Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT)
            lastToast.show()
            cancelNext = true
        }

        binding.counterStep.addTextChangedListener {
            if(binding.counterStep.text.isEmpty()) {
                binding.incrementBtn.text = "INCREMENT"
                binding.decrementBtn.text = "DECREMENT"
                step = 1
            } else {
                step = binding.counterStep.text.toString().toInt()
                binding.incrementBtn.text = "+$step"
                binding.decrementBtn.text = "-$step"
            }
        }

        binding.mainMenuBtn.setOnClickListener{
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }

    }
}