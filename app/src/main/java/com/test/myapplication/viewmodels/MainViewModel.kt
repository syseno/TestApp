package com.test.myapplication.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.myapplication.enums.UIViewState
import com.test.myapplication.models.Post
import com.test.myapplication.models.PostEntity
import com.test.myapplication.models.User
import com.test.myapplication.repositories.MainRepository
import com.test.myapplication.utils.mutableLiveDataOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    val uiState = mutableLiveDataOf<UIViewState>()
    val users = mutableLiveDataOf<List<User>>()
    val posts = mutableLiveDataOf<List<Post>>()
    val postEntities = mutableLiveDataOf<List<PostEntity>>()

    fun getUsers() {
        uiState.value = UIViewState.LOADING
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                val result = repository.getUsers()
                when (result.size) {
                    0 -> {
                        uiState.value = UIViewState.ERROR
                    }
                    else -> {
                        users.postValue(result)
                    }
                }
            }

        }
    }

    fun getPosts() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                val result = repository.getPost()
                posts.postValue(result)
                when (result.size) {
                    0 -> {
                        uiState.value = UIViewState.ERROR
                    }
                    else -> {
                        uiState.value = UIViewState.SUCCESS
                        val entities = arrayListOf<PostEntity>()
                        result?.forEach { post ->
                            entities.add(
                                PostEntity(
                                    id = post.id,
                                    title = post.title,
                                    body = post.body,
                                    userId = post.userId,
                                    username = users.value?.filter { it.id == post.userId }
                                        ?.getOrNull(0)?.username,
                                    company = users.value?.filter { it.id == post.userId }
                                        ?.getOrNull(0)?.company
                                )
                            )
                        }
                        postEntities.postValue(entities)
                    }
                }
            }
        }
    }
}