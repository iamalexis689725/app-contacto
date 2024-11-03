package com.example.aplicacioncontactos.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplicacioncontactos.R
import com.example.aplicacioncontactos.databinding.ActivityPersonaFormBinding
import com.example.aplicacioncontactos.models.Persona
import com.example.aplicacioncontactos.ui.viewmodels.PersonaListViewModel

class PersonaFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersonaFormBinding
    private val viewModel: PersonaListViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPersonaFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.btnGuardar.setOnClickListener{
            guardarContacto()
        }


    }

    private fun guardarContacto() {
        val persona = Persona(
            name = binding.txtNombre.text.toString(),
            last_name = binding.txtApellido.text.toString(),
            company = binding.txtCompania.text.toString(),
            address = binding.txtDireccion.text.toString(),
            city = binding.txtCiudad.text.toString(),
            state = binding.txtEstado.text.toString()
        )

        viewModel.addPersona(persona,
            onSuccess = {
                Toast.makeText(this, "Contacto guardado", Toast.LENGTH_SHORT).show()
                finish()
            },
            onError = {
                Toast.makeText(this, "Error al guardar el contacto", Toast.LENGTH_SHORT).show()
            }
        )

    }
}