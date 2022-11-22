package com.example.proyecto10mo.interfaces

import com.example.proyecto10mo.modelos.Restaurantes
import com.example.proyecto10mo.modelos.Usuarios

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface APIService {
        @FormUrlEncoded
        @POST("login")
        suspend fun login(@Field("email") email: String, @Field("password") password: String):Response<Usuarios>

        @GET
        suspend fun getRestaurantes(@Url url: String): Response<ArrayList<Restaurantes>>
}