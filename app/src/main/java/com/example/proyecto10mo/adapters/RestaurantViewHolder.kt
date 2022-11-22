package com.example.proyecto10mo.adapters

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto10mo.RestaurantesActivity
import com.example.proyecto10mo.VerRestaurant
import com.example.proyecto10mo.databinding.ItemRestaurantBinding
import com.example.proyecto10mo.modelos.Restaurantes
import com.example.proyecto10mo.modelos.Usuarios
import kotlinx.android.synthetic.main.activity_restaurantes.view.*
import java.io.Serializable

class RestaurantViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemRestaurantBinding.bind(view)

    fun bind(restaurant: Restaurantes) {
        binding.nombre.text = restaurant.nombre
        binding.sucursal.text = restaurant.sucursal
        binding.direccion.text = restaurant.domicilio

        var restaurantNombre = restaurant.nombre
        var restaurantSucursal = restaurant.sucursal
        var restaurantDomicilio = restaurant.domicilio
        var restaurantID = restaurant.id.toString()

        binding.nombre.setOnClickListener { v ->
            val intent = Intent(v.context,VerRestaurant::class.java)
            intent.putExtra("restaurantNombre", restaurantNombre as String)
                .putExtra("restaurantSucursal", restaurantSucursal as String)
                .putExtra("restaurantDomicilio", restaurantDomicilio as String)
                .putExtra("restaurantID", restaurantID as String)

            v.context.startActivity(intent)
        }
    }
}