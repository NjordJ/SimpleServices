package com.iruda.servicesexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iruda.servicesexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonSimpleService.setOnClickListener {
            startService(FirstService.newIntent(this, 25))
        }
    }
}