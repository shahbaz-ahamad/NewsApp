package com.shahbaz.news.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shahbaz.news.datamodel.Article
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insetNews(article:Article)

    @Delete
    suspend fun deleted(article: Article)

    @Query("SELECT * FROM Article")
    fun getNews():Flow<List<Article>>
}
