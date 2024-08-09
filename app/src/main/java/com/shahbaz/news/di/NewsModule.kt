package com.shahbaz.news.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shahbaz.news.presentation.bookmarkrepo.BookMarkRepo
import com.shahbaz.news.presentation.onboardingrepo.OnBoardingRepo
import com.shahbaz.news.retrofit.NewApi
import com.shahbaz.news.room.NewsDao
import com.shahbaz.news.room.NewsDatabase
import com.shahbaz.news.room.NewsTypeConverter
import com.shahbaz.news.util.Constant.BASE_URL
import com.shahbaz.news.util.Constant.DATABASE_NAME
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


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            DATABASE_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(newsDatabase: NewsDatabase):NewsDao{
        return newsDatabase.newsDAO()
    }

    @Provides
    @Singleton
    fun provideBookMarkRepo(newsDao: NewsDao):BookMarkRepo {
        return BookMarkRepo(newsDao)
    }
}

