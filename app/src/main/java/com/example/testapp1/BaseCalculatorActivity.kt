package com.example.testapp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class BaseCalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_calculator)
    }
}