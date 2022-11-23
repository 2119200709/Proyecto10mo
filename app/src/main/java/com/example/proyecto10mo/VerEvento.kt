package com.example.proyecto10mo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyecto10mo.databinding.ActivityVerEventoBinding

class VerEvento : AppCompatActivity() {

    // Binding
    lateinit var binding: ActivityVerEventoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerEventoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}