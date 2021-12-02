package com.test.myapplication.networks.responses

import com.test.myapplication.models.User

data class UserResponse(
	val userResponse: List<User> = listOf()
)
