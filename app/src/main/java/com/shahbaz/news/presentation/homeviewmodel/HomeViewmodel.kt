package com.shahbaz.news.presentation.homeviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.shahbaz.news.presentation.homerepo.HomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewmodel @Inject constructor(
    private val homeRepo: HomeRepo
) : ViewModel() {

    val newsList = homeRepo.getNews(
        listOf("bbc-news", "abc-news", "al-jazeera-english")
    ).cachedIn(viewModelScope)


}