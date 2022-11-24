package com.example.proyecto10mo.modelos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Invitaciones(
    @SerializedName("id") var id: Int,
    @SerializedName("nombre") var nombre: String,
    @SerializedName("evento") var evento: String,
    @SerializedName("codacc") var codacc: String,
    @SerializedName("status") var status: String,
): Serializable
