package com.example.aplicacioncontactos.api

import com.example.aplicacioncontactos.models.Comments
import com.example.aplicacioncontactos.models.Post
import com.example.aplicacioncontactos.models.Posts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JSONPlaceHolderService {

    @GET("posts/{id}")
    fun getPostById(@Path("id") id: Int): Call<Post>

    @GET("posts")
    fun getPostsList(): Call<Posts>

    @GET("posts/{id}/comments")
    fun getCommentsByPostId(@Path("id") id: Int): Call<Comments>
}