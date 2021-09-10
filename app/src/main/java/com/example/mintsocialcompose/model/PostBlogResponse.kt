package com.example.mintsocialcompose.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostBlogResponse(
    val blogId: String
)
