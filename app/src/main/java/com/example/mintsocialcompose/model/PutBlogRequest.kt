package com.example.mintsocialcompose.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PutBlogRequest(
    val blogId: String,
    val title: String,
    val content: String,
    val imageUrl: String,
    val createdDate: String,
    val authorId: String,
    val authorEmail: String,
    val isSponsored: Boolean
)
