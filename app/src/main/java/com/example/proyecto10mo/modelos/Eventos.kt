package com.example.proyecto10mo.modelos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Eventos(
    @SerializedName("id") var id: Int,
    @SerializedName("nombre") var nombre: String,
    @SerializedName("categoria") var categoria: String,
    @SerializedName("dia") var dia: String,
    @SerializedName("hora") var hora: String,
    @SerializedName("restaurante") var restaurante: String,
    @SerializedName("disponibles") var disponibles: String,
    @SerializedName("descripcion") var descripcion: String,
): Serializable
/*
[
   {
      "id":1,
      "nombre":"Posada",
      "categorias":"Cena de Navidad",
      "dia":"Viernes",
      "hora":"07:00PM",
      "restaurante":" Restaurant Angelo",
      "disponibles":50,
      "descripcion":"Posada para los colaboradores de ..."
   },
   {
      "id":2,
      "nombre":"Evento de A\u00f1o Nuevo",
      "categorias":"A\u00f1o Nuevo",
      "dia":"Mi\u00e9rcoles",
      "hora":"06:00PM",
      "restaurante":" Bacco Grill & Restaurant",
      "disponibles":100,
      "descripcion":"Evento de a\u00f1o nuevo para los colaboradores de..."
   }
]
* */