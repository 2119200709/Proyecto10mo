package com.example.proyecto10mo.modelos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Usuarios(
    @SerializedName("id") var id: Int?,
    @SerializedName("nombre") var nombre: String?,
    @SerializedName("apellido") var apellido: String?,
    @SerializedName("email") var email: String?,
    @SerializedName("password") var password: String?,
    @SerializedName("confirmado") var confirmado: Int?,
    @SerializedName("token") var token: String?,
    @SerializedName("admin") var admin: Int?,
    @SerializedName("msg") var msg: String
): Serializable
