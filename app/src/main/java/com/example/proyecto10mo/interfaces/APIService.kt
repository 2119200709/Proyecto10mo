package com.example.proyecto10mo.interfaces

import com.example.proyecto10mo.modelos.Eventos
import com.example.proyecto10mo.modelos.ResponseStatus
import com.example.proyecto10mo.modelos.Restaurantes
import com.example.proyecto10mo.modelos.Usuarios

import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Url

interface APIService {

        /**Login**/
        @FormUrlEncoded
        @POST("login")
        suspend fun login(@Field("email") email: String, @Field("password") password: String):Response<Usuarios>

        /**Restaurantes**/
        @GET
        suspend fun getRestaurantes(@Url url: String): Response<ArrayList<Restaurantes>>
        /**Editar Restaurantes**/
        @FormUrlEncoded
        @PUT("editarrestaurant/")
        suspend fun editarRestaurant(@Field("nombre") nombre: String, @Field("sucursal") sucursal: String, @Field("domicilio") domicilio: String, @Field("id") id: Int): Response<Restaurantes>
        /**Eliminar Restaurantes**/
        @DELETE
        suspend fun eliminarRestaurant(@Url url: String): Response<ResponseStatus>

        /**Eventos**/
        @GET
        suspend fun getEventos(@Url url: String): Response<ArrayList<Eventos>>
}