package com.example.proyecto10mo.interfaces

import com.example.proyecto10mo.modelos.Restaurantes
import com.example.proyecto10mo.modelos.Usuarios

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Url

interface APIService {
        @FormUrlEncoded
        @POST("login")
        suspend fun login(@Field("email") email: String, @Field("password") password: String):Response<Usuarios>

        @GET
        suspend fun getRestaurantes(@Url url: String): Response<ArrayList<Restaurantes>>

        @FormUrlEncoded
        @PUT("editarrestaurant/")
        suspend fun editarRestaurant(@Field("nombre") nombre: String, @Field("sucursal") sucursal: String, @Field("domicilio") domicilio: String, @Field("id") id: Int): Response<Restaurantes>
}