package com.example.testapp1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.testapp1.databinding.ActivityMainBinding


class ClickerActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private var counter: Int = 0
    private var step: Int = 1
    private var cancelNext = false


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        var lastToast = Toast(applicationContext)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.incrementBtn.setOnClickListener {
            /*counter += step
            binding.counterValue.text = counter.toString()
            val text = (counter - step).toString() + " + " + step + " = " + counter
            if(cancelNext) lastToast.cancel()
            lastToast = Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT)
            lastToast.show()
            cancelNext = true
            */

        }

        binding.decrementBtn.setOnClickListener {
            counter -= step
            binding.counterValue.text = counter.toString()
            val text = (counter - step).toString() + " - " + step + " = " + counter
            if(cancelNext) lastToast.cancel()
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

    }
}