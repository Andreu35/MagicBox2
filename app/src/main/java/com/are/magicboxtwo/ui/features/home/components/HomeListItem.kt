package com.are.magicboxtwo.ui.features.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.are.magicboxtwo.R
import com.are.magicboxtwo.data.model.Movie
import com.are.magicboxtwo.ui.theme.Red
import com.are.magicboxtwo.ui.theme.Red1

@Composable
fun HomeListItem(
    item: Movie = Movie()
) {
    if (item.backdrop_path != null) {
        AsyncImage(
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(5.dp)
                )
                .size(
                    width = 100.dp,
                    height = 150.dp
                ),
            model = stringResource(id = R.string.base_image, item.poster_path),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
    } else {
        Box(
            modifier = Modifier
                .border(
                    width = Dp.Hairline,
                    color = Red,
                    shape = RoundedCornerShape(5.dp)
                )
                .size(
                    width = 80.dp,
                    height = 150.dp
                )
        ) {
            Image(
                modifier = Modifier
                    .align(
                        alignment = Alignment.Center
                    )
                    .size(40.dp),
                painter = painterResource(id = R.drawable.ic_twotone_error_24),
                colorFilter = ColorFilter.tint(Red1),
                contentDescription = null
            )
        }
    }
}

