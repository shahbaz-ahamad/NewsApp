package com.shahbaz.news.bottomnavigaton

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.shahbaz.news.R
import com.shahbaz.news.navigation.Route

data class BottomNavigationItem(
    val route:String,
    val title:String,
    @DrawableRes val icon:Int,
    val selectedIcon :ImageVector
)


val bottomNavigationItem = listOf(
    BottomNavigationItem(
        route =Route.HomeScreen.route ,
        title = "Home",
        icon = R.drawable.ic_home,
        selectedIcon = Icons.Filled.Home
    ),
    BottomNavigationItem(
        route =Route.SearchScreen.route ,
        title = "Search",
        icon = R.drawable.ic_search,
        selectedIcon = Icons.Filled.Search
    ),
    BottomNavigationItem(
        route =Route.BookmarkScreen.route ,
        title = "Bookmark",
        icon = R.drawable.ic_bookmark,
        selectedIcon = Icons.Filled.Star
    )
)
