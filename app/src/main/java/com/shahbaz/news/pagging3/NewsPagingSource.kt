package com.shahbaz.news.pagging3

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shahbaz.news.datamodel.Article
import com.shahbaz.news.retrofit.NewApi

class NewsPagingSource(
    private val newApi: NewApi,
    private val source: String
) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    private var totalNewsCount = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val newsResponse = newApi.getNews(sources = source, page = page)
            totalNewsCount = totalNewsCount + newsResponse.articles.size
            val article = newsResponse.articles.distinctBy {
                it.title
            }
            LoadResult.Page(
                data = article,
                nextKey = if(totalNewsCount == newsResponse.totalResults) null else page +1 ,
                prevKey = if(totalNewsCount == 0)null else page-1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}