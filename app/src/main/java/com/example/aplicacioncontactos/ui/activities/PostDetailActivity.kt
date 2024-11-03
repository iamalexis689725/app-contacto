package com.example.aplicacioncontactos.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplicacioncontactos.ui.viewmodels.MainViewModel
import com.example.aplicacioncontactos.R
import com.example.aplicacioncontactos.databinding.ActivityPostDetailBinding
import com.example.aplicacioncontactos.ui.fragments.CommentFragment

class PostDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostDetailBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // setupEventListener()
        loadPostInfo()
        setupViewModelObservers()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadPostInfo() {
        val postId = intent.getIntExtra(POST_ID_PARAM, 0)
        if (postId == 0)
            return
        viewModel.getPostById(postId)
        binding.fragmentContainerView.post{
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, CommentFragment.newInstance(postId))
                .commit()
        }
    }

    private fun setupViewModelObservers() {
        viewModel.title.observe(this) {

            supportActionBar?.title = it
        }
        viewModel.body.observe(this) {
            binding.lblBody.text = it
        }
    }

    private fun setupEventListener() {
        binding.btnLoadApi.setOnClickListener{
            viewModel.getPostById(1)
        }
    }

    companion object {
        private const val POST_ID_PARAM = "postId"
        fun newIntent(context: Context, id: Int): Intent {
            return Intent(context, PostDetailActivity::class.java).apply {
                putExtra(POST_ID_PARAM, id)
            }
        }
    }
}