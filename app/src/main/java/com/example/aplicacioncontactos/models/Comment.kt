package com.example.aplicacioncontactos.models

data class Comment(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)
typealias Comments = ArrayList<Comment>