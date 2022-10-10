package com.are.magicboxtwo

import android.app.UiModeManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.are.magicboxtwo.ui.features.home.components.HomeScreen
import com.are.magicboxtwo.ui.features.home.viewmodel.HomeScreenViewModel
import com.are.magicboxtwo.ui.theme.MBTheme
import com.are.magicboxtwo.ui.theme.MagicBox2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HomeScreenViewModel by viewModels()

    private lateinit var uiModeManager: UiModeManager

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        uiModeManager = getSystemService(UI_MODE_SERVICE) as UiModeManager

        setContent {
            MagicBox2Theme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    containerColor = MBTheme.colors.onPrimary,
                    contentColor = MBTheme.colors.primary,
                    topBar = {
                        TopAppBar(
                            modifier = Modifier,
                            title = {
                                Text(
                                    text = stringResource(id = R.string.app_name),
                                    color = MBTheme.colors.primary
                                )
                            },
                            navigationIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_menu),
                                    contentDescription = null
                                )
                            },
                            actions = {
                                IconButton(
                                    onClick = {

                                    },
                                    content = {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_search),
                                            contentDescription = null
                                        )
                                    }
                                )
                                IconButton(
                                    onClick = {
                                        switchNightMode()
                                    },
                                    content = {
                                        Icon(
                                            painter = if (isSystemInDarkTheme()) {
                                                painterResource(id = R.drawable.ic_dark_mode)
                                            } else {
                                                painterResource(id = R.drawable.ic_light_mode)
                                            },
                                            contentDescription = null
                                        )
                                    }
                                )
                            }
                        )
                    }
                ) {
                    HomeScreen(
                        modifier = Modifier
                            .padding(it),
                        homeUIState = viewModel.uiState,
                        homeUIStateResponse = viewModel.movieListState.value,
                        onTryAgain = {}
                    )
                }
            }
        }

        viewModel.getMoviesByPage("Avengers", 1)
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
