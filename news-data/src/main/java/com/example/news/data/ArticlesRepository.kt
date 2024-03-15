package com.example.news.data

import com.example.news.database.NewsDataBase
import com.example.newsapi.NewsApi
import kotlinx.coroutines.flow.Flow

class ArticlesRepository(
    private val dataBase: NewsDataBase,
    private val api: NewsApi,
) {
    fun request(): Flow<Article>
}