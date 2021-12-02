package com.test.myapplication.viewmodels

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.myapplication.enums.UIViewState
import com.test.myapplication.models.Album
import com.test.myapplication.models.Photo
import com.test.myapplication.models.User
import com.test.myapplication.repositories.MainRepository
import com.test.myapplication.ui.activities.UserDetailActivity
import com.test.myapplication.utils.mutableLiveDataOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserDetailViewModel(private val repository: MainRepository) : ViewModel() {
    val user = mutableLiveDataOf<User>()
    val photos = mutableLiveDataOf<List<Photo>>()
    val albums = mutableLiveDataOf<List<Album>>()
    val uiState = mutableLiveDataOf<UIViewState>()
    var userId: Int = 0

    fun processIntent(intent: Intent) {
        userId = intent.getIntExtra(UserDetailActivity.EXTRA_USER_ID, 0)
    }

    fun getPhotos() {
        uiState.value = UIViewState.LOADING
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                val result = repository.getPhotos()
                photos.postValue(result)
            }
        }
    }

    fun getUser() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                val result = repository.getUserById(userId)
                if (result.id == 0) {
                    uiState.value = UIViewState.ERROR
                } else {
                    user.postValue(result)
                }
            }
        }
    }

    fun getAlbumsByUser() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                val result = repository.getAlbumsByUser(userId)
                if (result.isEmpty()) {
                    uiState.value = UIViewState.ERROR
                } else {
                    uiState.value = UIViewState.SUCCESS
                    albums.postValue(result)
                }
            }
        }
    }

}