package com.shahbaz.news.di

import android.content.Context
import com.shahbaz.news.presentation.onboardingrepo.OnBoardingRepo
import com.shahbaz.news.retrofit.NewApi
import com.shahbaz.news.util.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    @Provides
    @Singleton
    fun provideOnBoardingRepo(@ApplicationContext context: Context): OnBoardingRepo {
        return OnBoardingRepo(context)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit):NewApi{
        return retrofit.create(NewApi::class.java)
    }
}

