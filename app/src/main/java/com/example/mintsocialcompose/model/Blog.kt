package com.example.mintsocialcompose.model

data class Blog(
    val blogId: String,
    val title: String,
    val content: String,
    val imageUrl: String,
    val authorId: String,
    val authorEmail: String,
    val isSponsored: Boolean,
    val authorName: String? = "Unknown",
) {
    companion object {
        fun getBlogList(): List<Blog> {
            return listOf(
                Blog(
                    "",
                    "Title",
                    "content...",
                    "imageurl",
                    "",
                    "",
                    true,
                    ""
                ),
                Blog(
                    "",
                    "Title",
                    "content...",
                    "imageurl",
                    "",
                    "",
                    true,
                    ""
                ),
                Blog(
                    "",
                    "Title",
                    "content...",
                    "imageurl",
                    "",
                    "",
                    true,
                    ""
                ),
                Blog(
                    "",
                    "Title",
                    "content...",
                    "imageurl",
                    "",
                    "",
                    true,
                    ""
                ),
                Blog(
                    "",
                    "Title",
                    "content...",
                    "imageurl",
                    "",
                    "",
                    true,
                    ""
                ),
                Blog(
                    "",
                    "Title",
                    "content...",
                    "imageurl",
                    "",
                    "",
                    true,
                    ""
                ),
                Blog(
                    "",
                    "Title",
                    "content...",
                    "imageurl",
                    "",
                    "",
                    true,
                    ""
                ),
                Blog(
                    "",
                    "Title",
                    "content...",
                    "imageurl",
                    "",
                    "",
                    true,
                    ""
                ),
                Blog(
                    "",
                    "Title",
                    "content...",
                    "imageurl",
                    "",
                    "",
                    true,
                    ""
                ),
                Blog(
                    "",
                    "Title",
                    "content...",
                    "imageurl",
                    "",
                    "",
                    true,
                    ""
                ),
                Blog(
                    "",
                    "Title",
                    "content...",
                    "imageurl",
                    "",
                    "",
                    true,
                    ""
                ),
                Blog(
                    "",
                    "Title",
                    "content...",
                    "imageurl",
                    "",
                    "",
                    true,
                    ""
                ),
                Blog(
                    "",
                    "Title",
                    "content...",
                    "imageurl",
                    "",
                    "",
                    true,
                    ""
                ),
            )
        }
    }
}

