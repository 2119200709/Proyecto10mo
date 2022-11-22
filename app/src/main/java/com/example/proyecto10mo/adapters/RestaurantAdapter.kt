package com.example.proyecto10mo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto10mo.R
import com.example.proyecto10mo.modelos.Restaurantes

class RestaurantAdapter(private val restaurantes: MutableList<String>):
    RecyclerView.Adapter<RestaurantViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RestaurantViewHolder(layoutInflater.inflate(R.layout.item_restaurant, parent, false))
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val item: String = restaurantes[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = restaurantes.size

}