package com.example.mintsocialcompose.repository

import com.example.mintsocialcompose.api.BlogApi
import com.example.mintsocialcompose.model.Blog
import com.example.mintsocialcompose.model.PostBlogRequest
import com.example.mintsocialcompose.model.PutBlogRequest
import com.example.mintsocialcompose.model.toDataModel

import javax.inject.Inject

class BlogRepository @Inject constructor(
    private val blogApi: BlogApi
) {

    suspend fun getBlogs(authorId: String?, authorFilter: String?): List<Blog> {
        return blogApi.getBlogs(authorId, authorFilter).map { it.toDataModel() }
    }

    suspend fun postBlog(body: PostBlogRequest): String {
        return blogApi.postBlog(body).blogId
    }

    suspend fun getBlogById(blogId: String): Blog {
        return blogApi.getBlogById(blogId).toDataModel()
    }

    suspend fun updateBlogById(blogId: String, body: PutBlogRequest) {
        blogApi.updateBlogById(blogId, body)
    }
}