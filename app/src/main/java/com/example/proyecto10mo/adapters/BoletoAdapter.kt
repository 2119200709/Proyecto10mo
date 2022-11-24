package com.example.proyecto10mo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto10mo.R
import com.example.proyecto10mo.modelos.Invitaciones

class BoletoAdapter(private val invitaciones: ArrayList<Invitaciones>):
    RecyclerView.Adapter<BoletoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoletoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BoletoViewHolder(layoutInflater.inflate(R.layout.item_invitacion, parent, false))
    }

    override fun onBindViewHolder(holder: BoletoViewHolder, position: Int) {
        holder.bind(invitaciones[position])
    }

    override fun getItemCount(): Int = invitaciones.size


}