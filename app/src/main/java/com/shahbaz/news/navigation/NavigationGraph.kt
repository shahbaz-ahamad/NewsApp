package com.shahbaz.news.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shahbaz.news.bottomnavigaton.BottomNavigationBar
import com.shahbaz.news.bottomnavigaton.bottomNavigationItem
import com.shahbaz.news.datamodel.Article
import com.shahbaz.news.datamodel.Source
import com.shahbaz.news.presentation.AppScreen.BookMarkScreen
import com.shahbaz.news.presentation.AppScreen.HomeScreen
import com.shahbaz.news.presentation.AppScreen.detailsscreen.DetailScreen
import com.shahbaz.news.presentation.onbaordingscreen.OnBoardingScreen
import com.shahbaz.news.presentation.onboardingviewmodel.OnBoardingViewModel
import com.shahbaz.news.util.Constant.ARTICEL_ARGUMENT

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    startRoute: String
) {
    val navController = rememberNavController()
    var article: Article? = null
    Scaffold(
        bottomBar = {
            if (currentRoute(navController = navController) in bottomNavigationItem.map {
                    it.route
                })
                BottomNavigationBar(navController = navController)
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
                        BookMarkScreen(navController = navController)
                }

                composable(
                    route = Route.DetailsScreen.route,
                ) { backStactEntry ->


                    //now fetching the parcelable that is passed from the home screen

                    LaunchedEffect(key1 = backStactEntry) {
                        article =
                            navController.previousBackStackEntry?.savedStateHandle?.get<Article>(
                                ARTICEL_ARGUMENT
                            )

                    }
                    if (article != null) {
                        DetailScreen(
                            article = article!!, navController = navController
                        )
                    }


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