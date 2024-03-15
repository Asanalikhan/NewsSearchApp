package com.example.news.data

import com.example.news.data.model.Article
import com.example.news.data.model.toArticle
import com.example.newsapi.NewsApi
import com.example.news.database.NewsDataBase
import com.example.newsapi.models.ArticleDTO
import com.example.newsapi.models.ResponceDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArticlesRepository(
    private val dataBase: NewsDataBase,
    private val api: NewsApi,
) {
    fun getAll(): RequestResult<Flow<List<Article>>>{
        return RequestResult.InProgress(
            dataBase.articlesDao
                .getAll()
                .map { articles -> articles.map { it.toArticle() } }
        )
    }

    suspend fun search(query: String): Result<ResponceDTO<ArticleDTO>> {
        return api.everything() // Implement your search logic here
    }
}


sealed class RequestResult<E>(protected val data: E?) {
    class InProgress<E>(data: E?) : RequestResult<E>(data)
    class Success<E>(data: E?): RequestResult<E>(data)
    class Error<E>(data: E?):RequestResult<E>(data)

}
