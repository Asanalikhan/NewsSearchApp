package com.example.news.data

import com.example.news.data.model.Article
import com.example.news.database.NewsDataBase
import com.example.newsapi.NewsApi
import kotlinx.coroutines.flow.Flow

class ArticlesRepository(
    private val dataBase: NewsDataBase,
    private val api: NewsApi,
) {
    fun getAll(): RequestResult<Flow<List<Article>>>{
        return RequestResult.InProgress(
            dataBase.articlesDao
                .getAll()
                .map { articles -> articles.map { it.toArticle()}}
        )
    }
    suspend fun search(query: String): Flow<Article>{
        api.everything()
    }
}

sealed class RequestResult<E>(protected val data: E?) {
    class InProgress<E>(data: E?) : RequestResult<E>(data)
    class Success<E>(data: E?): RequestResult<E>(data)
    class Error<E>(data: E?):RequestResult<E>(data)

}
