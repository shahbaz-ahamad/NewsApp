package com.shahbaz.news.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shahbaz.news.datamodel.Article


@Database(entities = [Article::class], version = 1)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDatabase :RoomDatabase() {
    abstract fun newsDAO() :NewsDao
}