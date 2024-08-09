package com.shahbaz.news.presentation.bookmarkrepo

import com.shahbaz.news.datamodel.Article
import com.shahbaz.news.room.NewsDao
import kotlinx.coroutines.flow.Flow

class BookMarkRepo(
    private val newsDao: NewsDao
) {
    suspend fun addBookMark(article: Article) {
        newsDao.insetNews(article)
    }

    fun getNews():Flow<List<Article>>{
        return newsDao.getNews()
    }

    suspend fun deleteArticle(article: Article){
        newsDao.deleted(article)
    }
}