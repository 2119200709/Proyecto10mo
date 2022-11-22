package com.example.proyecto10mo.modelos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseStatus(
    @SerializedName("msg") var msg: String?
): Serializable
