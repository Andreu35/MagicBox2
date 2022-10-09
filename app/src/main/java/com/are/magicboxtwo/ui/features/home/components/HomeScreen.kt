package com.are.magicboxtwo.ui.features.home.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.are.magicboxtwo.ui.theme.MagicBox2Theme

@Composable
fun HomeScreen(
    imageUrl: String = "https://wallpaperaccess.com/full/3770392.jpg"
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth(),
            model = imageUrl,
            contentDescription = null,
            filterQuality = FilterQuality.High
        )
    }
}

@Composable
@Preview(name = "HomeScreen Light")
@Preview(name = "HomeScreen Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun HomeScreenPreview() {
    MagicBox2Theme {
        HomeScreen(

        )
    }
}
