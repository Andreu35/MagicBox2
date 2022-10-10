package com.are.magicboxtwo

import android.app.UiModeManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.are.magicboxtwo.ui.common.navigationdrawer.MagicBoxNavigationDrawer
import com.are.magicboxtwo.ui.common.topappbar.ActionType
import com.are.magicboxtwo.ui.common.topappbar.MagicBoxTopAppBar
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
                MagicBoxNavigationDrawer {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize(),
                        containerColor = MBTheme.colors.onPrimary,
                        contentColor = MBTheme.colors.primary,
                        topBar = {
                            MagicBoxTopAppBar(
                                onActionClick = { type ->
                                    onActionClick(type)
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
        }

        viewModel.getMoviesByPage("Avengers", 1)
    }

    private fun onActionClick(type: ActionType) {
        when (type) {
            ActionType.DRAWER -> {

            }
            ActionType.SEARCH -> {

            }
            ActionType.LIGHT_DARK -> switchNightMode()
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
