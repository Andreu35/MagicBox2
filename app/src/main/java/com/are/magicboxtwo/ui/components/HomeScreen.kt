package com.are.magicboxtwo.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.are.magicboxtwo.ui.theme.MBTheme
import com.are.magicboxtwo.ui.theme.MagicBox2Theme

@Composable
fun HomeScreen(
    // content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .height(100.dp)
            .background(
                color = MBTheme.colors.background
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    color = MBTheme.colors.primary
                )
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    color = MBTheme.colors.secondary
                )
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    color = MBTheme.colors.tertiary
                )
        )
    }
}

@Composable
@Preview(name = "HomeScreen Light")
@Preview(name = "HomeScreen Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun HomeScreenPreview() {
    MagicBox2Theme {
        HomeScreen()
    }
}
