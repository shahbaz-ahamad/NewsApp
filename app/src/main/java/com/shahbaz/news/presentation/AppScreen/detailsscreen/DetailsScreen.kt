package com.shahbaz.news.presentation.AppScreen.detailsscreen

import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shahbaz.news.R
import com.shahbaz.news.datamodel.Article
import com.shahbaz.news.datamodel.Source
import com.shahbaz.news.presentation.bookmarkviewmodel.BookMarkViewmodel

@Composable
fun DetailScreen(
    article: Article, navController: NavController
) {
    val context = LocalContext.current
    val bookMarkViewmodel:BookMarkViewmodel = hiltViewModel()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        DetailScreenTopBar(
            onBackClick = { navController.navigateUp() },
            onBookMarkClick = {
                bookMarkViewmodel.inertNews(article)
                Toast.makeText(context,"Data Saved",Toast.LENGTH_SHORT).show()
                Log.d("Data inserted","yes")
            },
            onShareClick = {
                Intent(
                    Intent.ACTION_SEND
                ).also {
                    it.putExtra(Intent.EXTRA_TEXT,article.url)
                    it.type = "text/plain"

                    //this portion will check whether is there any app to handle the intent or not
                    if(it.resolveActivity(context.packageManager)!= null){
                        context.startActivity(it)
                    }
                }
            },
            onBrowsingClick = {
                Log.d("BrowsingClick", "Button clicked")
                Intent(
                    Intent.ACTION_VIEW
                ).also {
                    it.data = Uri.parse(article.url)
                    if(it.resolveActivity(context.packageManager)!= null){
                        context.startActivity(it)
                    }else{
                        Log.d("BrowsingClick", "No browser app found")
                    }
                }
            },
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(
                start = 24.dp, end = 24.dp, top = 10.dp
            )
        ) {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context).data(article.urlToImage).build(),
                    contentDescription = article.description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(248.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(id = R.color.text_title)
                    )

                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(id = R.color.body)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun DetailsScreenPreview() {
    DetailScreen(
        article = Article(
            author = "",
            content = "We use cookies and data to Deliver and maintain Google services Track outages and protect against spam, fraud, and abuse Measure audience engagement and site statistics to unde… [+1131 chars]",
            description = "",
            publishedAt = "2 hours",
            source = Source(id = "", name = "BBC"),
            title = "Her train broke down. Her phone died. And then she met her Saver in a",
            url = "",
            urlToImage = "https://ichef.bbci.co.uk/live-experience/cps/624/cpsprodpb/11787/production/_124395517_bbcbreakingnewsgraphic.jpg"
        ), navController = rememberNavController()
    )
}
