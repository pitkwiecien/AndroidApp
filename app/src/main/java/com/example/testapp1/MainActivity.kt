package com.example.testapp1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.testapp1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var counter: Int = 0
    private var step: Int = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.incrementBtn.setOnClickListener{
            counter += step
            binding.counterValue.text = counter.toString()
            Toast.makeText(applicationContext, (counter - step).toString() + " + " + step + " = " + counter, Toast.LENGTH_LONG).show()
        }

        binding.decrementBtn.setOnClickListener{
            counter -= step
            binding.counterValue.text = counter.toString()
            Toast.makeText(applicationContext, (counter - step).toString() + " + " + step + " = " + counter, Toast.LENGTH_LONG).show()
        }

        binding.counterStep.addTextChangedListener{
            if(binding.counterStep.text.length == 0){
                binding.incrementBtn.text = "INCREMENT"
                binding.decrementBtn.text = "DECREMENT"
            } else {
                step = binding.counterStep.text.toString().toInt()
                binding.incrementBtn.text = "+$step"
                binding.decrementBtn.text = "-$step"
            }

        }
    }
}