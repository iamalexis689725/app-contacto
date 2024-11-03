package com.example.aplicacioncontactos.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aplicacioncontactos.models.Comments
import com.example.aplicacioncontactos.repositories.CommentRepository
import com.example.aplicacioncontactos.repositories.PostRepository

class CommentListViewModel: ViewModel() {

    private val _commentList = MutableLiveData<Comments>().apply {
        value = arrayListOf()
    }

    val commentList: LiveData<Comments> = _commentList

    fun getComments(postId: Int) {
        CommentRepository.getCommentList(
            postId,
            onSuccess = {
                _commentList.value = it
            }, onError = {
                it.printStackTrace()
            }
        )
    }
}