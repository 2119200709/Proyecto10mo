package com.example.proyecto10mo.modelos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Restaurantes(
    @SerializedName("id") var id: Int?,
    @SerializedName("nombre") var nombre: String?,
    @SerializedName("sucursal") var sucursal: String?,
    @SerializedName("domicilio") var domicilio: String?,
    @SerializedName("imagen") var password: String?,
    @SerializedName("tags") var confirmado: Int?,
    @SerializedName("redes") var token: String?,
): Serializable
