package com.shahbaz.news.presentation.AppScreen

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shahbaz.news.R
import com.shahbaz.news.datamodel.Article
import com.shahbaz.news.datamodel.Source
import com.shahbaz.news.navigation.Route
import com.shahbaz.news.presentation.homeviewmodel.HomeViewmodel
import com.shahbaz.news.util.Constant.ARTICEL_ARGUMENT
import java.net.ConnectException


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewmodel: HomeViewmodel = hiltViewModel(),
    navHostController: NavHostController
) {
    val context = LocalContext.current
    val response = homeViewmodel.newsList.collectAsLazyPagingItems()

    val titles by remember {
        derivedStateOf {
            if (response.itemCount > 10) {
                response.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83D\uDFE5 ") {
                        it.title
                    }

            } else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = titles, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp)
                .basicMarquee(), fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )

        Spacer(modifier = Modifier.height(24.dp))

        when {
            response.loadState.refresh is LoadState.Loading -> {
                ShimmerList()
            }

            response.loadState.refresh is LoadState.Error -> {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }

            else -> {
                LazyColumn(
                    modifier = modifier.fillMaxSize()
                ) {
                    items(response.itemCount) {
                        response[it]?.let { article ->
                            ArticleCard(
                                modifier = Modifier.padding(
                                    horizontal = 24.dp,
                                    vertical = 10.dp
                                ),
                                article = article,
                                onClick = {

                                    //this is the one of the simple way of passing parcelable among the screen
                                    navHostController.currentBackStackEntry?.savedStateHandle?.set(
                                        key = ARTICEL_ARGUMENT,
                                        value = article
                                    )
                                    navHostController.navigate(Route.DetailsScreen.route)
                                }
                            )
                        }
                    }
                }
            }
        }

    }

}


@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit
) {

    val context = LocalContext.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
    ) {
        AsyncImage(
            modifier = Modifier
                .size(100.dp)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context = context).data(article.urlToImage).build(),
            contentDescription = article.description,
            contentScale = ContentScale.Crop
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .height(100.dp)
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium.copy(),
                color = colorResource(id = R.color.text_title),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body)
                )

                Spacer(modifier = Modifier.width(6.dp))

                Icon(
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = "time Icon"
                )

                Spacer(modifier = Modifier.width(2.dp))

                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelSmall,
                    color = colorResource(id = R.color.body),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticlePreview() {
    ArticleCard(
        article =
        Article(
            author = "",
            content = "",
            description = "",
            publishedAt = "2 hours",
            source = Source(id = "", name = "BBC"),
            title = "Her train broke down. Her phone died. And then she met her Saver in a",
            url = "",
            urlToImage = "https://ichef.bbci.co.uk/live-experience/cps/624/cpsprodpb/11787/production/_124395517_bbcbreakingnewsgraphic.jpg"
        )
    ) {

    }
}

@Composable
fun ShimmerList() {
    LazyColumn {
        items(10) {
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(
                    horizontal = 24.dp,
                    vertical = 10.dp
                )
            )
        }
    }
}
