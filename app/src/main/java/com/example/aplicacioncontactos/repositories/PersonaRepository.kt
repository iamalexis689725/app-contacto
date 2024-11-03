package com.example.aplicacioncontactos.repositories

import com.example.aplicacioncontactos.api.JSONPlaceHolderService
import com.example.aplicacioncontactos.api.JSONPlaceHolderService2
import com.example.aplicacioncontactos.models.Persona
import com.example.aplicacioncontactos.models.Post
import com.example.aplicacioncontactos.models.Personas
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object PersonaRepository {

    fun getPersonaList(onSuccess: (Personas) -> Unit, onError: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository2.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService2::class.java)
        service.getPersonasList().enqueue(object : Callback<Personas> {
            override fun onResponse(call: Call<Personas>, response: Response<Personas>) {
                if (response.isSuccessful) {
                    val persona = response.body()
                    onSuccess(persona!!)
                }
            }

            override fun onFailure(call: Call<Personas>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }

    fun addPersona(persona: Persona, onSuccess: (Persona) -> Unit, onError: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository2.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService2::class.java)
        service.addPersona(persona).enqueue(object : Callback<Persona> {
            override fun onResponse(call: Call<Persona>, response: Response<Persona>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    onSuccess(post!!)
                }
            }

            override fun onFailure(call: Call<Persona>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }

    fun updatePersona(persona: Persona, onSuccess: (Persona) -> Unit, onError: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository2.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService2::class.java)
        service.updatePersona(persona.id!!, persona).enqueue(object : Callback<Persona> {
            override fun onResponse(call: Call<Persona>, response: Response<Persona>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    onSuccess(post!!)
                }
            }

            override fun onFailure(call: Call<Persona>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }
}