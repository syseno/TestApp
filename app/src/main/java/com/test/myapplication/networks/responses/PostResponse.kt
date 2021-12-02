package com.test.myapplication.networks.responses

import com.google.gson.annotations.SerializedName
import com.test.myapplication.models.Post

data class PostResponse(

	val postResponse: List<Post> = emptyList()
)
