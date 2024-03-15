package com.example.news.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.news.database.dao.ArticleDao
import com.example.news.database.models.ArticleDBO
import com.example.news.database.utils.Converters


class NewsDataBase internal constructor(private val dataBase: NewsRoomDataBase){
    val articlesDao: ArticleDao
        get() = dataBase.articlesDao()
}
@Database(entities = [ArticleDBO::class], version = 1)
@TypeConverters(Converters::class)
internal abstract class NewsRoomDataBase: RoomDatabase() {
    abstract fun articlesDao(): ArticleDao
}

fun NewsDataBase(applicationContext: Context): NewsDataBase {
    val newsRoomDataBase = Room.databaseBuilder(
        checkNotNull(applicationContext.applicationContext),
        NewsRoomDataBase::class.java, "news"
    ).build()
    return NewsDataBase(newsRoomDataBase)
}