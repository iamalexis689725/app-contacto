package com.example.aplicacioncontactos.api

import com.example.aplicacioncontactos.models.Persona
import com.example.aplicacioncontactos.models.Personas
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface JSONPlaceHolderService2 {

    @GET("personas")
    fun getPersonasList(): Call<Personas>

    @POST("personas")
    fun addPersona(@Body persona: Persona): Call<Persona>

    fun updatePersona(id: Long, @Body persona: Persona): Call<Persona>
}