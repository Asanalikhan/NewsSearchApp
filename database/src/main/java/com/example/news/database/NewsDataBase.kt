package com.example.news.database

import android.content.Context
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.news.database.models.ArticleDBO
import com.example.news.database.utils.Converters

@Database(entities = [ArticleDBO::class], version = 1)
@TypeConverters(Converters::class)
abstract class NewsDataBase: RoomDatabase() {
    abstract fun articlesDao(): ArticleDBO
}

fun NewsDataBase(applicationContext: Context): NewsDataBase {
    return Room.databaseBuilder(
        checkNotNull(applicationContext.applicationContext),
        NewsDataBase::class.java, "news"
    ).build()
}