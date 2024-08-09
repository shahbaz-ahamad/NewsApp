package com.shahbaz.news.presentation.bookmarkviewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahbaz.news.datamodel.Article
import com.shahbaz.news.presentation.bookmarkrepo.BookMarkRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.State
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class BookMarkViewmodel @Inject constructor(
    private val bookMarkRepo: BookMarkRepo
) : ViewModel() {

    private val _state = mutableStateOf<List<Article>>(emptyList())
    val state: State<List<Article>> = _state

    init {
        getNews()
    }

    fun inertNews(article: Article) {
        viewModelScope.launch {
            bookMarkRepo.addBookMark(article)
        }
    }

    fun getNews() {
        viewModelScope.launch {
            bookMarkRepo.getNews().onEach {
                _state.value =it
            }.launchIn(viewModelScope)
        }
    }

    fun deleteArticle(article: Article) {
        viewModelScope.launch {
            bookMarkRepo.deleteArticle(article)
        }
    }


}