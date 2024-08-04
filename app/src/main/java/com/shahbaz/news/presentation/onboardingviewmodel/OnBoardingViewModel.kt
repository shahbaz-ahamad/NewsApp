package com.shahbaz.news.presentation.onboardingviewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahbaz.news.navigation.Route
import com.shahbaz.news.presentation.onboardingrepo.OnBoardingRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val onBoardingRepo: OnBoardingRepo
) : ViewModel() {

    private val _splashScreenCondition = mutableStateOf(true)
    val splashScreenState = _splashScreenCondition

    private val _startDestination = mutableStateOf(Route.AppStartNavigation.route)
    val startDestination = _startDestination


    init {
        viewModelScope.launch {
            onBoardingRepo.readAppEntry().collect { entryPoint ->
                if (entryPoint) {
                    _startDestination.value = Route.NewsNavigation.route
                } else {
                    _startDestination.value = Route.AppStartNavigation.route
                }

                delay(200) //without delay onboarding screen will get appeared
                _splashScreenCondition.value = false
            }
        }
    }


    fun saveAppEntry() {
        viewModelScope.launch {
            onBoardingRepo.saveAppEntry()
        }
    }

}