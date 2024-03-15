package com.example.news.data

import com.example.news.data.model.Article
import com.example.news.database.NewsDataBase
import com.example.newsapi.NewsApi
import kotlinx.coroutines.flow.Flow

class ArticlesRepository(
    private val dataBase: NewsDataBase,
    private val api: NewsApi,
) {
    suspend fun request(): Flow<Article>{
        TODO("Not implemented")
    }
}