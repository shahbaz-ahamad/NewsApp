package com.shahbaz.news.retrofit

import com.shahbaz.news.datamodel.NewsResponse
import com.shahbaz.news.util.Constant.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewApi {
    @GET("everything")
    suspend fun getNews(
        @Query("sources") sources:String,
        @Query("page") page:Int,
        @Query("apiKey") apiKey:String = API_KEY
    ):NewsResponse
}