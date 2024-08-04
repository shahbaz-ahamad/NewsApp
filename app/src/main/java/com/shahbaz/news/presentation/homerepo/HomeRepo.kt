package com.shahbaz.news.presentation.homerepo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shahbaz.news.datamodel.Article
import com.shahbaz.news.pagging3.NewsPagingSource
import com.shahbaz.news.retrofit.NewApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepo @Inject constructor(
    private val newApi: NewApi
) {
    fun getNews(source: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { NewsPagingSource(newApi, source.joinToString(",")) }
        ).flow
    }
}