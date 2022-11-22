package com.example.proyecto10mo.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto10mo.databinding.ItemRestaurantBinding
import com.example.proyecto10mo.modelos.Restaurantes

class RestaurantViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemRestaurantBinding.bind(view)

    fun bind(restaurant: String) {
        binding.iv.text = restaurant

        binding.iv.setOnClickListener {  }
    }
}