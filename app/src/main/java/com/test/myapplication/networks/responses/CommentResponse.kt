package com.test.myapplication.networks.responses

import com.google.gson.annotations.SerializedName
import com.test.myapplication.models.Comment

data class CommentResponse(

	val commentResponse: List<Comment>? = null
)
