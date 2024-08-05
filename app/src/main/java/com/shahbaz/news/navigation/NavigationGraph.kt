package com.shahbaz.news.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.shahbaz.news.bottomnavigaton.BottomNavigationBar
import com.shahbaz.news.bottomnavigaton.bottomNavigationItem
import com.shahbaz.news.presentation.AppScreen.HomeScreen
import com.shahbaz.news.presentation.onbaordingscreen.OnBoardingScreen
import com.shahbaz.news.presentation.onboardingviewmodel.OnBoardingViewModel

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    startRoute: String
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            if (currentRoute(navController = navController) in bottomNavigationItem.map {
                    it.route
                })
                BottomNavigationBar(navController =navController)
        }
    ) { innerpadding ->
        NavHost(
            navController = navController,
            startDestination = startRoute,
            modifier = Modifier.padding(innerpadding)
        ) {
            navigation(
                startDestination = Route.OnBoardingScreen.route,
                route = Route.AppStartNavigation.route
            ) {
                composable(
                    route = Route.OnBoardingScreen.route
                ) {
                    val onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
                    OnBoardingScreen(
                        onClick = onBoardingViewModel::saveAppEntry
                    )
                }
            }

            navigation(
                startDestination = Route.HomeScreen.route,
                route = Route.NewsNavigation.route
            ) {


                composable(
                    route = Route.HomeScreen.route
                ) {
                    HomeScreen(navHostController = navController)
                }


                composable(
                    route = Route.SearchScreen.route
                ) {

                }


                composable(
                    route = Route.BookmarkScreen.route
                ) {

                }

                composable(
                    route = Route.DetailsScreen.route
                ) {

                }
            }
        }
    }

}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}