package com.example.proyecto10mo.adapters

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto10mo.VerEvento
import com.example.proyecto10mo.databinding.ItemEventoBinding
import com.example.proyecto10mo.modelos.Eventos

class EventoViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemEventoBinding.bind(view)

    fun bind(evento: Eventos) {
        binding.nombre.text = evento.nombre
        binding.categoria.text = evento.categoria
        binding.dia.text = evento.dia
        binding.hora.text = evento.hora
        binding.descripcion.text = evento.descripcion
        binding.disponibles.text = "Disponibilidad para: "+evento.disponibles +" personas"
        binding.restaurante.text = evento.restaurante

        val id = evento.id.toString()
        val nombre = evento.nombre
        val categoria = evento.categoria
        val dia = evento.dia
        val hora = evento.hora
        val descripcion = evento.descripcion
        val disponibles = evento.disponibles
        val restaurante = evento.restaurante

        binding.nombre.setOnClickListener { v ->
            val intent = Intent(v.context, VerEvento::class.java)
                .putExtra("id", id as String)
                .putExtra("nombre", nombre as String)
                .putExtra("categoria", categoria as String)
                .putExtra("dia", dia as String)
                .putExtra("hora", hora as String)
                .putExtra("descripcion", descripcion as String)
                .putExtra("disponibles", disponibles as String)
                .putExtra("restaurante", restaurante as String)
            v.context.startActivity(intent)
        }
    }

}