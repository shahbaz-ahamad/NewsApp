package com.shahbaz.news.presentation.onbaordingscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shahbaz.news.R
import org.w3c.dom.Text

@Composable
fun NewsButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
        )
    }
}


@Composable
fun NewsTextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {

    TextButton(
        modifier = modifier,
        onClick = { onClick() }) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = colorResource(id = R.color.textButtonColor)

        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsbuttonPreview() {

    Column {
        NewsButton(
            text = "Next Button"
        ) {

        }

        Spacer(modifier = Modifier.height(30.dp))

        NewsTextButton(text = "Go Back") {

        }
    }

}