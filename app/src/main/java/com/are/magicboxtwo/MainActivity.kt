package com.are.magicboxtwo

import android.app.UiModeManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.are.magicboxtwo.ui.common.navigationdrawer.MagicBoxNavigationDrawer
import com.are.magicboxtwo.ui.common.topappbar.ActionType
import com.are.magicboxtwo.ui.common.topappbar.MagicBoxTopAppBar
import com.are.magicboxtwo.ui.common.topappbar.SearchState
import com.are.magicboxtwo.ui.features.home.components.HomeScreen
import com.are.magicboxtwo.ui.features.home.viewmodel.HomeScreenViewModel
import com.are.magicboxtwo.ui.theme.MBTheme
import com.are.magicboxtwo.ui.theme.MagicBox2Theme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HomeScreenViewModel by viewModels()
    private lateinit var uiModeManager: UiModeManager

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (viewModel.uiState.value.searchState == SearchState.Closed) {
                isEnabled = false
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this@MainActivity, onBackPressedCallback)

        uiModeManager = getSystemService(UI_MODE_SERVICE) as UiModeManager

        setContent {
            MagicBox2Theme {
                val scope = rememberCoroutineScope()
                val drawerState = rememberDrawerState(
                    initialValue = DrawerValue.Closed
                )
                MagicBoxNavigationDrawer(
                    drawerState = drawerState
                ) {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize(),
                        containerColor = MBTheme.colors.onPrimary,
                        contentColor = MBTheme.colors.primary,
                        topBar = {
                            MagicBoxTopAppBar(
                                uiState = viewModel.uiState.collectAsState().value,
                                onDrawerClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                },
                                onActionClick = { type ->
                                    onActionClick(type)
                                },
                                onSearch = { query ->
                                    viewModel.searchMovies(query)
                                },
                                onSearchClose = {
                                    viewModel.openOrCloseSearchBar(SearchState.Closed)
                                }
                            )
                        }
                    ) {
                        HomeScreen(
                            modifier = Modifier
                                .padding(it),
                            homeUIState = viewModel.uiState.collectAsState().value,
                            homeUIStateResponse = viewModel.movieListState.value,
                            onTryAgain = {}
                        )
                    }
                }
            }
        }
    }

    private fun onActionClick(type: ActionType) {
        when (type) {
            ActionType.SEARCH -> {
                viewModel.openOrCloseSearchBar(SearchState.Searching)
            }
            ActionType.LIGHT_DARK -> {
                switchNightMode()
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
}
