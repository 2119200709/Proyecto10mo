package com.example.proyecto10mo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyecto10mo.databinding.ActivityVerBoletoBinding

class VerBoleto : AppCompatActivity() {

    lateinit var binding: ActivityVerBoletoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerBoletoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val codacc : String? = intent.getStringExtra("codigo")

        binding.code.text = codacc

        binding.volver.setOnClickListener {
            finish()
        }
    }
}