package com.shahbaz.news.presentation.AppScreen

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.shahbaz.news.navigation.Route
import com.shahbaz.news.presentation.bookmarkviewmodel.BookMarkViewmodel
import com.shahbaz.news.util.Constant
import com.shahbaz.news.util.Constant.ARTICEL_ARGUMENT
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookMarkScreen(
    navController: NavController
) {

    val context = LocalContext.current
    val bookMarkViewmodel: BookMarkViewmodel = hiltViewModel()
    val article by bookMarkViewmodel.state

    var isRemoved by remember {
        mutableStateOf(false)
    }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 5.dp),
            text = "Bookmark",
            style = MaterialTheme.typography.headlineMedium
        )

        if (article.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(article) { index, data ->
                    val dismissState = rememberDismissState(
                        confirmStateChange = {
                            if (it == DismissValue.DismissedToStart || it == DismissValue.DismissedToEnd) {
                                isRemoved = true
                                bookMarkViewmodel.deleteArticle(data)
                                true
                            } else false
                        }
                    )


                    SwipeToDismiss(
                        state = dismissState,
                        directions = setOf(
                            DismissDirection.StartToEnd,
                            DismissDirection.EndToStart
                        ),
                        background = {

                        },
                        dismissContent = {
                            ArticleCard(
                                modifier = Modifier.padding(
                                    horizontal = 24.dp,
                                    vertical = 10.dp
                                ),
                                article = data,
                                onClick = {
                                    navController.currentBackStackEntry?.savedStateHandle?.set(
                                        ARTICEL_ARGUMENT,
                                        data
                                    )
                                    navController.navigate(Route.DetailsScreen.route)
                                }
                            )
                        }
                    )

            }


        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No Data Saved", fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}

}