package com.example.aplicacioncontactos.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacioncontactos.R
import com.example.aplicacioncontactos.databinding.ActivityPostListBinding
import com.example.aplicacioncontactos.models.Post
import com.example.aplicacioncontactos.ui.adapters.PostListAdapter
import com.example.aplicacioncontactos.ui.viewmodels.PostListViewModel

class PostListActivity : AppCompatActivity(), PostListAdapter.PostItemListener {

    lateinit var binding: ActivityPostListBinding
    private val viewModel: PostListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPostListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupViewModelObservers()
        setupRecyclerView()
        viewModel.getPostList()
    }

    private fun setupRecyclerView() {
        val adapter = PostListAdapter(this)
        binding.rvPostList.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@PostListActivity)
        }
    }

    private fun setupViewModelObservers() {
        viewModel.postList.observe(this) {
            val adapter = binding.rvPostList.adapter as PostListAdapter
            adapter.updateData(it)
        }
    }

    override fun onPostItemClick(post: Post) {
        val intent = PostDetailActivity.newIntent(this, post.id)
        startActivity(intent)
    }
}