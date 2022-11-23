package com.example.proyecto10mo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto10mo.R
import com.example.proyecto10mo.modelos.Eventos

class EventoAdapter(private val eventos: ArrayList<Eventos>):
    RecyclerView.Adapter<EventoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EventoViewHolder(layoutInflater.inflate(R.layout.item_evento, parent, false))
    }

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        holder.bind(eventos[position])
    }

    override fun getItemCount(): Int = eventos.size

}