package com.example.freatures

import com.example.news.data.ArticlesRepository
import com.example.news.data.model.Article
import kotlinx.coroutines.flow.Flow

class GetAllArticlesUseCase(private val repository: ArticlesRepository) {

    operator fun invoke(): Flow<Article> {
        return repository.getAll()
    }
}