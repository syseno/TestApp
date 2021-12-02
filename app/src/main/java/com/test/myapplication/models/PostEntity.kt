package com.test.myapplication.models

import java.io.Serializable

data class PostEntity(
    var id: Int? = null,
    var title: String? = null,
    var body: String? = null,
    var userId: Int? = null,
    var username: String? = null,
    var company: Company? = null
): Serializable