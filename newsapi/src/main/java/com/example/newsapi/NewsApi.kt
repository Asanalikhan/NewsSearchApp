package com.example.newsapi

import androidx.annotation.IntRange
import com.example.newsapi.models.Article
import com.example.newsapi.models.Languages
import com.example.newsapi.models.Responce
import com.example.newsapi.models.SortBy
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Date
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

interface NewsApi {

    @GET("/everything")
    suspend fun everything(
        @Query("q") query: String? = null,
        @Query("from") from: Date? = null,
        @Query("to") to: Date? = null,
        @Query("languages") languages: List<Languages>? = null,
        @Query("sortBy") sortBy: SortBy? = null,
        @Query("pageSize") @IntRange(from = 0, to = 100) pageSize: Int = 100,
        @Query("page") @IntRange(from = 1) page: Int = 1,
    ): Result<Responce<Article>>
}

fun NewsApi(
    baseUrl: String,
    okHttpClient: OkHttpClient? = null,
    json: Json = Json,
): NewsApi {
    return retrofit(baseUrl, okHttpClient, json).create()
}

private fun retrofit(
    baseUrl: String,
    okHttpClient: OkHttpClient?,
    json: Json,
): Retrofit {
    val contentType = MediaType.get("application/json")
    val converterFactory = json.asConverterFactory(contentType)

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(converterFactory)
        .client(okHttpClient ?: OkHttpClient())
        .build()
}
