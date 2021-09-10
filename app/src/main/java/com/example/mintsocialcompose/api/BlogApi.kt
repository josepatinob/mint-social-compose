package com.example.mintsocialcompose.api

import com.example.mintsocialcompose.model.BlogResponse
import com.example.mintsocialcompose.model.PostBlogRequest
import com.example.mintsocialcompose.model.PostBlogResponse
import com.example.mintsocialcompose.model.PutBlogRequest
import retrofit2.http.*

interface BlogApi {
    companion object {
        const val BLOG_BASE_URL = "https://6zlt50gfbe.execute-api.us-east-2.amazonaws.com/dev/"
    }

    @GET("blogs")
    suspend fun getBlogs(
        @Query("authorId") authorId: String? = null,
        @Query("authorFilter") authorFilter: String? = null
    ): List<BlogResponse>

    @POST("blogs")
    suspend fun postBlog(
        @Body blog: PostBlogRequest
    ): PostBlogResponse

    @GET(value = "blogs/{blogId}")
    suspend fun getBlogById(
        @Path("blogId") blogId: String
    ): BlogResponse

    @PUT(value = "blogs/{blogId}")
    suspend fun updateBlogById(
        @Path("blogId") blogId: String,
        @Body blog: PutBlogRequest
    )
}