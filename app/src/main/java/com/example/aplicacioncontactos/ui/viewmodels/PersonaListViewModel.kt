package com.example.aplicacioncontactos.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aplicacioncontactos.models.Persona
import com.example.aplicacioncontactos.models.Personas
import com.example.aplicacioncontactos.repositories.PersonaRepository

class PersonaListViewModel: ViewModel() {


    private val _personaList = MutableLiveData<Personas>().apply {
        value = arrayListOf()
    }

    val personaList: LiveData<Personas> = _personaList

    fun getPersonaList() {
        PersonaRepository.getPersonaList(
            onSuccess = {
                _personaList.value = it
            }, onError = {
                it.printStackTrace()
            }
        )
    }

//    fun addPersona() {
//        val persona = Persona(
//            name = "Santiago",
//            last_name = "Perez",
//            company = "Google",
//            address = "Calle 123",
//            city = "CDMX",
//            state = "CDMX"
//        )
//        PersonaRepository.addPersona(
//            persona,
//            onSuccess = {
//                getPersonaList()
//            }, onError = {
//                it.printStackTrace()
//            }
//        )
//    }

    fun addPersona(persona: Persona, onSuccess: () -> Unit, onError: (Throwable) -> Unit) {
        PersonaRepository.addPersona(
            persona,
            onSuccess = {
                getPersonaList()
                onSuccess()
            },
            onError = { onError(it) }
        )
    }

    fun updatePersona(persona: Persona, onSuccess: () -> Unit, onError: (Throwable) -> Unit) {
        PersonaRepository.updatePersona(
            persona,
            onSuccess = {
                getPersonaList()
                onSuccess()
            },
            onError = { onError(it) }
        )
    }

}