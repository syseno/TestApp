package com.test.myapplication.viewmodels

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.myapplication.enums.UIViewState
import com.test.myapplication.models.Comment
import com.test.myapplication.models.Post
import com.test.myapplication.repositories.MainRepository
import com.test.myapplication.ui.activities.MainActivity
import com.test.myapplication.ui.activities.UserDetailActivity
import com.test.myapplication.utils.mutableLiveDataOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailPostViewModel(private val repository: MainRepository) : ViewModel() {
    val post = mutableLiveDataOf<Post>()
    val comments = mutableLiveDataOf<List<Comment>>()
    val uiState = mutableLiveDataOf<UIViewState>()
    var postId: Int = 0
    var userId: Int = 0
    var userName: String? = ""

    fun processIntent(intent: Intent) {
        postId = intent.getIntExtra(MainActivity.EXTRA_POST_ID, 0)
        userName = intent.getStringExtra(MainActivity.EXTRA_USER_NAME)
        userId = intent.getIntExtra(UserDetailActivity.EXTRA_USER_ID, 0)
    }

    fun getPost() {
        uiState.value = UIViewState.LOADING
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                val result = repository.getPostById(postId)
                if (result.id == 0) {
                    uiState.value = UIViewState.ERROR
                } else {
                    post.postValue(result)
                }
            }
        }
    }

    fun getComments() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                val result = repository.getCommentsById(postId)
                if (result.isEmpty()) {
                    uiState.value = UIViewState.ERROR
                } else {
                    uiState.value = UIViewState.SUCCESS
                    comments.postValue(result)
                }
            }
        }
    }
}