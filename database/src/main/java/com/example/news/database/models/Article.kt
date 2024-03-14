package com.example.news.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.util.Date


@Entity
data class Article(
    @ColumnInfo("source")
    val source: Source,
    @ColumnInfo("author")
    val author: String,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("url")
    val url: String,
    @ColumnInfo("urlToImage")
    val urlToImage: String,
    @ColumnInfo("publishedAt")
    val publishedAt: Date,
    @ColumnInfo("content")
    val content: String
)

@Entity
data class Source(
    @ColumnInfo("id")
    val id: String,
    @ColumnInfo("name")
    val name: String
)