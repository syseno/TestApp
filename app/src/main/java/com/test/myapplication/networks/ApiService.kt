package com.test.myapplication.networks

import com.test.myapplication.base.BaseService
import com.test.myapplication.models.*

class ApiService(private val api: Api) : BaseService() {
    suspend fun getUsers(): Result<List<User>> {
        return createCall { api.getUser() }
    }

    suspend fun getPosts(): Result<List<Post>> {
        return createCall { api.getPost() }
    }

    suspend fun getCommentsById(postId: Int): Result<List<Comment>> {
        return createCall { api.getCommentsById(postId) }
    }

    suspend fun getPostsById(postId: Int): Result<Post> {
        return createCall { api.getPostById(postId) }
    }

    suspend fun getUserById(userId: Int): Result<User> {
        return createCall { api.getUserById(userId) }
    }

    suspend fun getAlbumsByUser(userId: Int): Result<List<Album>> {
        return createCall { api.getAlbumsByUser(userId) }
    }

    suspend fun getPhotos(): Result<List<Photo>> {
        return createCall { api.getPhotos() }
    }
}