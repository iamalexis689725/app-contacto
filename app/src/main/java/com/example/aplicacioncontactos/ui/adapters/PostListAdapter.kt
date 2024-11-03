package com.example.aplicacioncontactos.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacioncontactos.R
import com.example.aplicacioncontactos.databinding.PostItemLayoutBinding
import com.example.aplicacioncontactos.models.Post
import com.example.aplicacioncontactos.models.Posts

class PostListAdapter(
    private val listener: PostItemListener
): RecyclerView.Adapter<PostListAdapter.PostItemViewHolder>() {

    private var postList: Posts = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemViewHolder {
        val binding = PostItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostItemViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostItemViewHolder, position: Int) {
        holder.bind(postList[position], listener)
    }

    fun updateData(it: Posts) {
        postList = it
        notifyDataSetChanged()
    }


    class PostItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val lblPostItemTitle: TextView = itemView.findViewById(R.id.lblPostItemTitle)
        fun bind(post: Post, listener: PostItemListener) {
            lblPostItemTitle.text = post.title
            itemView.setOnClickListener {
                listener.onPostItemClick(post)
            }
        }
    }

    interface PostItemListener{
        fun onPostItemClick(post: Post)
    }
}