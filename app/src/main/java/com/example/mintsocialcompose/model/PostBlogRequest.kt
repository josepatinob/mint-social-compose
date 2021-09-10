package com.example.mintsocialcompose.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostBlogRequest(
    val title: String,
    val content: String,
    val imageUrl: String,
    val authorId: String,
    val authorEmail: String,
    val isSponsored: Boolean
)