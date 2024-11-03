package com.example.aplicacioncontactos.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aplicacioncontactos.models.Post
import com.example.aplicacioncontactos.models.Posts
import com.example.aplicacioncontactos.repositories.PostRepository

class PostListViewModel: ViewModel() {


    private val _postList = MutableLiveData<Posts>().apply {
        value = arrayListOf()
    }

    val postList: LiveData<Posts> = _postList

    fun getPostList() {
        PostRepository.getPostList(
            onSuccess = {
                _postList.value = it
            }, onError = {
                it.printStackTrace()
            }
        )
    }

}