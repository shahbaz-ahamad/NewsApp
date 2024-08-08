package com.shahbaz.news.presentation.AppScreen.detailsscreen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shahbaz.news.R
import com.shahbaz.news.ui.theme.NewsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenTopBar(
    onBackClick: () -> Unit,
    onBookMarkClick: () -> Unit,
    onShareClick: () -> Unit,
    onBrowsingClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = colorResource(id = R.color.body),
            navigationIconContentColor = colorResource(id = R.color.body)
        ),
        windowInsets = WindowInsets(top = 0.dp),
        title = {},
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = "back Button",
                    tint = colorResource(id = R.color.body)
                )
            }
        },
        actions = {
            IconButton(onClick = { onBookMarkClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bookmark),
                    contentDescription = "bookmark",
                    tint = colorResource(id = R.color.body)
                )
            }

            IconButton(onClick = { onShareClick() }) {
                Icon(imageVector = Icons.Default.Share, contentDescription = "share",tint = colorResource(id = R.color.body))
            }

            IconButton(onClick = { onBrowsingClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_network),
                    contentDescription = "browsing",
                    tint = colorResource(id = R.color.body)
                )
            }
        }
    )
}


@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun DetailScreenTopBarPreview() {
    NewsTheme {
        DetailScreenTopBar(
            onBrowsingClick = {},
            onShareClick = {},
            onBookMarkClick = {},
            onBackClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ab() {
    NewsTheme {
        DetailScreenTopBar(
            onBrowsingClick = {},
            onShareClick = {},
            onBookMarkClick = {},
            onBackClick = {}
        )
    }
}
