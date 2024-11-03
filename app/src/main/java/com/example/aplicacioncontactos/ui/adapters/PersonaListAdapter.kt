package com.example.aplicacioncontactos.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacioncontactos.R
import com.example.aplicacioncontactos.databinding.PersonaItemLayoutBinding
import com.example.aplicacioncontactos.models.Persona
import com.example.aplicacioncontactos.models.Personas

class PersonaListAdapter: RecyclerView.Adapter<PersonaListAdapter.PersonaItemViewHolder>() {

    private var personaList: Personas = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaItemViewHolder {
        val binding = PersonaItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonaItemViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return personaList.size
    }

    override fun onBindViewHolder(holder: PersonaItemViewHolder, position: Int) {
        holder.bind(personaList[position])
    }

    fun updateData(it: Personas) {
        personaList = it
        notifyDataSetChanged()
    }


    class PersonaItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val lblPersonaItemTitle: TextView = itemView.findViewById(R.id.lblPersonaItemTitle)
        fun bind(persona: Persona) {
            lblPersonaItemTitle.text = persona.name
        }
    }


}