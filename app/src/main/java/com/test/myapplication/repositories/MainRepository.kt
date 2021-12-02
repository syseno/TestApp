package com.test.myapplication.repositories

import com.test.myapplication.models.*
import com.test.myapplication.networks.ApiService

class MainRepository(private val service: ApiService) {
    suspend fun getUsers(): List<User> {
        return when (val result = service.getUsers()) {
            is Result.Success -> result.data
            is Result.Error -> throw result.error
        }
    }

    suspend fun getPost(): List<Post> {
        return when (val result = service.getPosts()) {
            is Result.Success -> result.data
            is Result.Error -> throw result.error
        }
    }

    suspend fun getPostById(postId: Int): Post {
        return when (val result = service.getPostsById(postId)) {
            is Result.Success -> result.data
            is Result.Error -> throw result.error
        }
    }

    suspend fun getCommentsById(postId: Int): List<Comment> {
        return when (val result = service.getCommentsById(postId)) {
            is Result.Success -> result.data
            is Result.Error -> throw result.error
        }
    }

    suspend fun getUserById(userId: Int): User {
        return when (val result = service.getUserById(userId)) {
            is Result.Success -> result.data
            is Result.Error -> throw result.error
        }
    }

    suspend fun getAlbumsByUser(userId: Int): List<Album> {
        return when (val result = service.getAlbumsByUser(userId)) {
            is Result.Success -> result.data
            is Result.Error -> throw result.error
        }
    }

    suspend fun getPhotos(): List<Photo> {
        return when (val result = service.getPhotos()) {
            is Result.Success -> result.data
            is Result.Error -> throw result.error
        }
    }
}