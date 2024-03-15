package com.example.news.data

import com.example.news.data.model.Article
import com.example.news.database.NewsDataBase
import com.example.newsapi.NewsApi
import kotlinx.coroutines.flow.Flow

class ArticlesRepository(
    private val dataBase: NewsDataBase,
    private val api: NewsApi,
) {
    suspend fun getAll(): Flow<List<Article>>{
        api.everything()
        return dataBase.articlesDao().getAll()
            .map{articles -> articles.map { it.toArticle }}
    }
    suspend fun search(query: String): Flow<Article>{
        api.everything()
    }

}