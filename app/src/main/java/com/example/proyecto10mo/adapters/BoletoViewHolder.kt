package com.example.proyecto10mo.adapters

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto10mo.VerBoleto
import com.example.proyecto10mo.VerRestaurant
import com.example.proyecto10mo.databinding.ItemInvitacionBinding
import com.example.proyecto10mo.modelos.Invitaciones

class BoletoViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemInvitacionBinding.bind(view)

    fun bind(inivitacion: Invitaciones) {
        binding.nombre.text = inivitacion.evento

        var code = inivitacion.codacc

        binding.nombre.setOnClickListener { v ->
            val intent = Intent(v.context, VerBoleto::class.java)
            intent.putExtra("codigo", code as String)

            v.context.startActivity(intent)
        }
    }
}