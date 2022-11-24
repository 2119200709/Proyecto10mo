package com.example.proyecto10mo.adapters

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto10mo.VerUser
import com.example.proyecto10mo.databinding.ItemRestaurantBinding
import com.example.proyecto10mo.databinding.ItemUserBinding
import com.example.proyecto10mo.modelos.Usuarios

class UserViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemUserBinding.bind(view)

    fun bind(user: Usuarios) {
        binding.nombre.text = user.nombre+" "+user.apellido
        binding.email.text = user.email
        binding.confirmado.text = if (user.confirmado.toString() == "1") { "Confirmado" } else { "No Confirmado" }
        binding.admin.text = if (user.admin.toString() == "1") { "Administrador" } else { "Cliente" }

        var userNombre = user.nombre
        var userApellido = user.apellido
        var userEmail = user.email
        var userRol = user.admin

        var userID = user.id.toString()

        binding.nombre.setOnClickListener { v ->
            val intent = Intent(v.context, VerUser::class.java)
            intent.putExtra("userNombre", userNombre as String)
                .putExtra("userApellido", userApellido as String)
                .putExtra("userEmail", userEmail as String)
                .putExtra("userID", userID as String)
                .putExtra("userRol", userRol)
            v.context.startActivity(intent)
        }
    }
}