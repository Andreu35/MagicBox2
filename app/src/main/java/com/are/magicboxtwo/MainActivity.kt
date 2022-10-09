package com.are.magicboxtwo

import android.app.UiModeManager
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.are.magicboxtwo.ui.features.home.components.HomeScreen
import com.are.magicboxtwo.ui.theme.MagicBox2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var uiModeManager: UiModeManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uiModeManager = getSystemService(UI_MODE_SERVICE) as UiModeManager

        setContent {
            MagicBox2Theme {
                HomeScreen()
            }
        }
    }

    private fun switchNightMode() {
        if (uiModeManager.nightMode == UiModeManager.MODE_NIGHT_YES) {
            uiModeManager.nightMode = UiModeManager.MODE_NIGHT_NO
        } else {
            uiModeManager.nightMode = UiModeManager.MODE_NIGHT_YES
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

@Preview(name = "MainActivity Light")
@Preview(name = "MainActivity Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    MagicBox2Theme {
        HomeScreen(

        )
    }
}