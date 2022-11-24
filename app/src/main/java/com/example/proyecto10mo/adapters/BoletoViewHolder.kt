package com.example.proyecto10mo.adapters

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto10mo.VerBoleto
import com.example.proyecto10mo.VerRestaurant
import com.example.proyecto10mo.databinding.ItemInvitacionBinding
import com.example.proyecto10mo.modelos.Invitaciones
import com.example.proyecto10mo.objects.VariablesGlobales

class BoletoViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemInvitacionBinding.bind(view)

    fun bind(inivitacion: Invitaciones) {
        binding.fechayhora.text = "${inivitacion.dia} ${inivitacion.hora}"
        if (VariablesGlobales.rol =="0") {
            binding.nombre.text = inivitacion.evento
            binding.status.text = inivitacion.restautant

        } else {
            binding.nombre.text = inivitacion.nombre
            binding.status.text = inivitacion.evento
        }
        var id = inivitacion.id.toString()
        var nombreCli = inivitacion.nombre
        var nombreEvento = inivitacion.evento
        var nombreRestaurante = inivitacion.restautant
        var domicilio = inivitacion.domicilio
        var fechayhora = inivitacion.dia+" "+inivitacion.hora

        var code = inivitacion.codacc
        binding.nombre.setOnClickListener { v ->
            val intent = Intent(v.context, VerBoleto::class.java)
            intent.putExtra("codigo", code as String)
            intent.putExtra("id", id as String)
            intent.putExtra("cliente", nombreCli)
            intent.putExtra("evento", nombreEvento)
            intent.putExtra("restaurant", nombreRestaurante)
            intent.putExtra("domicilio", domicilio)
            intent.putExtra("fechayhora", fechayhora)
            v.context.startActivity(intent)
        }
    }
}