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
                    "12ge3-bdeg33-gde433-ff3433",
                    "Title",
                    "content...",
                    "imageurl",
                    "",
                    "",
                    true,
                    ""
                ),
                Blog(
                    "12ge3-bdeg33-gde433-ff3433",
                    "Title",
                    "content...",
                    "imageurl",
                    "",
                    "",
                    true,
                    ""
                ),
                Blog(
                    "12ge3-bdeg33-gde433-ff3433",
                    "Title",
                    "content...",
                    "imageurl",
                    "",
                    "",
                    true,
                    ""
                ),
                Blog(
                    "12ge3-bdeg33-gde433-ff3433",
                    "Title",
                    "content...",
                    "imageurl",
                    "",
                    "",
                    true,
                    ""
                ),
                Blog(
                    "12ge3-bdeg33-gde433-ff3433",
                    "Title",
                    "content...",
                    "imageurl",
                    "",
                    "",
                    true,
                    ""
                ),
                Blog(
                    "12ge3-bdeg33-gde433-ff3433",
                    "Title",
                    "content...",
                    "imageurl",
                    "",
                    "",
                    true,
                    ""
                ),
                Blog(
                    "12ge3-bdeg33-gde433-ff3433",
                    "Title",
                    "content...",
                    "imageurl",
                    "",
                    "",
                    true,
                    ""
                ),
                Blog(
                    "12ge3-bdeg33-gde433-ff3433",
                    "Title",
                    "content...",
                    "imageurl",
                    "",
                    "",
                    true,
                    ""
                ),
                Blog(
                    "12ge3-bdeg33-gde433-ff3433",
                    "Title",
                    "content...",
                    "imageurl",
                    "",
                    "",
                    true,
                    ""
                ),
                Blog(
                    "12ge3-bdeg33-gde433-ff3433",
                    "Title",
                    "content...",
                    "imageurl",
                    "",
                    "",
                    true,
                    ""
                ),
                Blog(
                    "12ge3-bdeg33-gde433-ff3433",
                    "Title",
                    "content...",
                    "imageurl",
                    "",
                    "",
                    true,
                    ""
                ),
                Blog(
                    "12ge3-bdeg33-gde433-ff3433",
                    "Title",
                    "content...",
                    "imageurl",
                    "",
                    "",
                    true,
                    ""
                ),
                Blog(
                    "12ge3-bdeg33-gde433-ff3433",
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

