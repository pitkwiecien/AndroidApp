package com.example.testapp1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testapp1.databinding.ActivityMainMenuBinding

class   MainMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.clickerBtn.setOnClickListener{
            val intent = Intent(this, ClickerActivity::class.java)
            startActivity(intent)
        }

        binding.baseCalculatorBtn.setOnClickListener{
            val intent = Intent(this, BaseCalculatorActivity::class.java)
            startActivity(intent)
        }

        binding.quadraticCalculatorBtn.setOnClickListener{
            val intent = Intent(this, QuadraticCalculatorActivity::class.java)
            startActivity(intent)
        }
    }
}