package com.are.magicboxtwo.ui.features.home.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.are.magicboxtwo.ui.common.error.ErrorContent
import com.are.magicboxtwo.ui.features.home.state.HomeUIState
import com.are.magicboxtwo.ui.features.home.state.HomeUIStateResponse
import com.are.magicboxtwo.ui.features.home.state.dummyPreviewItems
import com.are.magicboxtwo.ui.theme.MBTheme
import com.are.magicboxtwo.ui.theme.MagicBox2Theme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeUIState: HomeUIState = HomeUIState(),
    homeUIStateResponse: HomeUIStateResponse,
    onTryAgain: () -> Unit
) {
    val scrollState = rememberLazyGridState()

    when (homeUIStateResponse) {
        is HomeUIStateResponse.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = MBTheme.colors.onPrimary,
                    strokeWidth = 4.dp
                )
            }
        }
        is HomeUIStateResponse.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                ErrorContent(
                    onClick = onTryAgain
                )
            }
        }
        is HomeUIStateResponse.Success -> {
            LazyVerticalGrid(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .then(modifier),
                columns = if (MBTheme.isLandscape) {
                    GridCells.Adaptive(100.dp)
                } else {
                    GridCells.Fixed(3)
                },
                contentPadding = PaddingValues(15.dp),
                state = scrollState
            ) {
                items(homeUIStateResponse.movieList) { item ->
                    HomeListItem(
                        item = item
                    )
                }
            }
        }
    }

}

@Composable
@Preview(name = "HomeScreen Light")
@Preview(name = "HomeScreen Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "HomeScreen Landscape", device = Devices.AUTOMOTIVE_1024p, widthDp = 720)
private fun HomeScreenPreview() {
    MagicBox2Theme {
        HomeScreen(
            homeUIState = HomeUIState(),
            homeUIStateResponse = HomeUIStateResponse.Success(dummyPreviewItems()),
            onTryAgain = {}
        )
    }
}
