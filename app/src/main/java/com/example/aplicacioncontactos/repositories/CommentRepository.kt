package com.example.aplicacioncontactos.repositories

import com.example.aplicacioncontactos.api.JSONPlaceHolderService
import com.example.aplicacioncontactos.models.Comments
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CommentRepository {
    fun getCommentList(
        id: Int,
        onSuccess: (Comments) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        service.getCommentsByPostId(id).enqueue(object : Callback<Comments> {
            override fun onResponse(call: Call<Comments>, response: Response<Comments>) {
                if (response.isSuccessful) {
                    val comments = response.body()
                    onSuccess(comments!!)
                }
            }

            override fun onFailure(call: Call<Comments>, t: Throwable) {
                onError(t)
            }
        }

        )
    }
}