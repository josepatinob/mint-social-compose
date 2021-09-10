package com.example.mintsocialcompose.model

import java.time.LocalDate

data class Blog(
    val blogId: String,
    val title: String,
    val content: String,
    val imageUrl: String,
    val createdDate: LocalDate,
    val authorId: String,
    val authorEmail: String,
    val isSponsored: Boolean,
    val authorName: String = "Unknown",
)

fun BlogResponse.toDataModel(): Blog =
    Blog(
        blogId,
        title,
        content,
        imageUrl,
        createdDate,
        authorId,
        authorEmail,
        isSponsored
    )

