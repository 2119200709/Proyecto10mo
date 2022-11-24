package com.example.proyecto10mo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import com.example.proyecto10mo.databinding.ActivityVerEventoBinding

class VerEvento : AppCompatActivity() {

    // Binding
    lateinit var binding: ActivityVerEventoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerEventoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val eventoID : String? = intent.getStringExtra("id")
        val eventoNombre : String? = intent.getStringExtra("nombre")
        val eventoCategoria : String? = intent.getStringExtra("categoria")
        val eventoDia : String? = intent.getStringExtra("dia")
        val eventoHora : String? = intent.getStringExtra("hora")
        val eventoDescripcion : String? = intent.getStringExtra("descripcion")
        val eventoDisponibles : String? = intent.getStringExtra("disponibles")
        val eventoRestaurante : String? = intent.getStringExtra("restaurante")

        binding.nombre.text = "Nombre: $eventoNombre"
        binding.categoria.text = "Categoria: $eventoCategoria"
        binding.dia.text = "Dia: $eventoDia"
        binding.hora.text = "Hora: $eventoHora"
        binding.descripcion.text = "Descripción del evento: $eventoDescripcion"
        binding.disponibles.text = "Cupo disponible: $eventoDisponibles"
        binding.restaurante.text = "Restaurant: $eventoRestaurante"

        binding.nombre.movementMethod = ScrollingMovementMethod()
        binding.descripcion.movementMethod = ScrollingMovementMethod()
        binding.restaurante.movementMethod = ScrollingMovementMethod()
    }
}