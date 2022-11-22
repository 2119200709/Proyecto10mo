package com.example.proyecto10mo.adapters

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto10mo.VerRestaurant
import com.example.proyecto10mo.databinding.ItemRestaurantBinding
import com.example.proyecto10mo.modelos.Restaurantes

class RestaurantViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemRestaurantBinding.bind(view)

    fun bind(restaurant: Restaurantes) {
        binding.nombre.text = restaurant.nombre
        binding.sucursal.text = restaurant.sucursal
        binding.direccion.text = restaurant.domicilio

        binding.nombre.setOnClickListener { v ->
            val intent = Intent(v.context,VerRestaurant::class.java)
            v.context.startActivity(intent)
        }
    }
}