package com.test.myapplication.networks

import com.test.myapplication.models.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("users")
    suspend fun getUser(): Response<List<User>>

    @GET("posts")
    suspend fun getPost(): Response<List<Post>>

    @GET("comments")
    suspend fun getCommentsById(
        @Query("postId") postId: Int
    ): Response<List<Comment>>

    @GET("posts/{postId}")
    suspend fun getPostById(
        @Path("postId") postId: Int
    ): Response<Post>

    @GET("users/{userId}")
    suspend fun getUserById(
        @Path("userId") postId: Int
    ): Response<User>

    @GET("users/{userId}/albums")
    suspend fun getAlbumsByUser(
        @Path("userId") userId: Int
    ): Response<List<Album>>

    @GET("photos")
    suspend fun getPhotos(): Response<List<Photo>>
}