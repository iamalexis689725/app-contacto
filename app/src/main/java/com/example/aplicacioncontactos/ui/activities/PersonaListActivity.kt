package com.example.aplicacioncontactos.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacioncontactos.R
import com.example.aplicacioncontactos.databinding.ActivityPersonaListBinding
import com.example.aplicacioncontactos.ui.adapters.PersonaListAdapter
import com.example.aplicacioncontactos.ui.adapters.PostListAdapter
import com.example.aplicacioncontactos.ui.viewmodels.PersonaListViewModel

class PersonaListActivity : AppCompatActivity() {

    lateinit var binding: ActivityPersonaListBinding
    private val viewModel: PersonaListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPersonaListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupViewModelObservers()
        setupEventListener()
        setupRecyclerView()
        viewModel.getPersonaList()

    }

    private fun setupEventListener() {
        binding.fabAddPersona.setOnClickListener {
            val intent = Intent(this, PersonaFormActivity::class.java)
            startActivity(intent)
            //viewModel.addPersona()
        }
    }

    private fun setupRecyclerView() {
        val adapter = PersonaListAdapter()
        binding.rvPersonaList.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@PersonaListActivity)
        }
    }

    private fun setupViewModelObservers() {
        viewModel.personaList.observe(this) {
            val adapter = binding.rvPersonaList.adapter as PersonaListAdapter
            adapter.updateData(it)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPersonaList()
    }


}