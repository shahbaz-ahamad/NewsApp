package com.shahbaz.news.navigation

import com.shahbaz.news.datamodel.Article



sealed class Route(
    val route: String
) {
    object OnBoardingScreen : Route(route = "onBoardingScreen")
    object HomeScreen : Route(route = "HomeScreen")
    object SearchScreen : Route(route = "SearchScreen")
    object BookmarkScreen : Route(route = "bookMarkScreen")
    object DetailsScreen : Route(route = "detailsScreen")
    object AppStartNavigation : Route(route = "appStartNavigation")
    object NewsNavigation : Route(route = "newsNavigation")
}