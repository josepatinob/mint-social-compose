package com.example.mintsocialcompose.model

import com.squareup.moshi.JsonClass
import java.time.LocalDate

@JsonClass(generateAdapter = true)
data class BlogResponse(
    val blogId: String,
    val title: String,
    val content: String,
    val imageUrl: String,
    val createdDate: LocalDate,
    val authorId: String,
    val authorEmail: String,
    val isSponsored: Boolean
)