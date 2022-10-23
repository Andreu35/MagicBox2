package com.are.magicboxtwo.ui.features.detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.are.magicboxtwo.R
import com.are.magicboxtwo.ui.common.error.ErrorContent
import com.are.magicboxtwo.ui.features.detail.state.DetailsUIStateResponse
import com.are.magicboxtwo.ui.theme.MBTheme

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    detailsUIStateResponse: DetailsUIStateResponse,
    onTryAgain: () -> Unit
) {
    when (detailsUIStateResponse) {
        is DetailsUIStateResponse.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = MBTheme.colors.primary,
                    strokeWidth = 4.dp
                )
            }
        }
        is DetailsUIStateResponse.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                ErrorContent(
                    onClick = onTryAgain
                )
            }
        }
        is DetailsUIStateResponse.Success -> {
            val movie = detailsUIStateResponse.movie
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(modifier),
                model = stringResource(id = R.string.base_image_details, movie.backdrop_path!!),
                contentDescription = movie.title
            )
        }
    }
}