package com.shahbaz.news

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import com.shahbaz.news.navigation.NavigationGraph
import com.shahbaz.news.presentation.homeviewmodel.HomeViewmodel
import com.shahbaz.news.presentation.onbaordingscreen.OnBoardingScreen
import com.shahbaz.news.presentation.onboardingrepo.OnBoardingRepo
import com.shahbaz.news.presentation.onboardingviewmodel.OnBoardingViewModel
import com.shahbaz.news.ui.theme.NewsTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val onBoardingViewModel by viewModels<OnBoardingViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        actionBar?.hide()

        installSplashScreen().apply {
            setKeepOnScreenCondition(
                condition = {
                    onBoardingViewModel.splashScreenState.value
                }
            )
        }
        setContent {
            NewsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavigationGraph(
                        modifier = Modifier.padding(innerPadding),
                        startRoute = onBoardingViewModel.startDestination.value
                    )

                }
            }
        }
    }
}

