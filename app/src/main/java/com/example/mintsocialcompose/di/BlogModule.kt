package com.example.mintsocialcompose.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mintsocialcompose.adapter.LocalDateAdapter
import com.example.mintsocialcompose.api.BlogApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BlogModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): BlogApi {
        return Retrofit.Builder()
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(LocalDateAdapter()).build()
                )
            )
            .baseUrl(BlogApi.BLOG_BASE_URL)
            .client(okHttpClient).build().create(BlogApi::class.java)
    }
}