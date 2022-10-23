package com.are.magicboxtwo

import android.app.UiModeManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.are.magicboxtwo.ui.common.navigation.MagicBoxNavHost
import com.are.magicboxtwo.ui.common.navigation.MagicBoxNavigationRoute
import com.are.magicboxtwo.ui.common.navigationdrawer.MagicBoxNavigationDrawer
import com.are.magicboxtwo.ui.common.topappbar.ActionType
import com.are.magicboxtwo.ui.common.topappbar.MagicBoxTopAppBar
import com.are.magicboxtwo.ui.common.topappbar.SearchState
import com.are.magicboxtwo.ui.features.detail.viewmodel.DetailScreenViewModel
import com.are.magicboxtwo.ui.features.home.viewmodel.HomeScreenViewModel
import com.are.magicboxtwo.ui.theme.MBTheme
import com.are.magicboxtwo.ui.theme.MagicBox2Theme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HomeScreenViewModel by viewModels()
    private val detailsViewModel: DetailScreenViewModel by viewModels()
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
                val navController = rememberNavController()
                val scope = rememberCoroutineScope()
                val drawerState = rememberDrawerState(
                    initialValue = DrawerValue.Closed
                )
                MagicBoxNavigationDrawer(
                    navController = navController,
                    drawerState = drawerState
                ) {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize(),
                        containerColor = MBTheme.colors.onPrimary,
                        contentColor = MBTheme.colors.primary,
                        topBar = {
                            MagicBoxTopAppBar(
                                navController = navController,
                                uiState = viewModel.uiState.collectAsState().value,
                                onDrawerClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                },
                                onBackClick = {
                                    onBackPressedDispatcher.onBackPressed()
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
                    ) { paddingValues ->
                        MagicBoxNavHost(
                            navController = navController,
                            paddingValues = paddingValues,
                            homeUIStateResponse = viewModel.movieListState.value,
                            detailsUIStateResponse = detailsViewModel.movieState.value,
                            onItemClick = { movie ->
                                if (viewModel.uiState.value.searchState == SearchState.Searching) {
                                    viewModel.openOrCloseSearchBar(SearchState.Closed)
                                }
                                viewModel.updateAppBarTitle(movie.title)
                                navController.navigate(
                                    route = "${MagicBoxNavigationRoute.Detail.name}/${movie.id}"
                                ) {
                                    popUpTo(MagicBoxNavigationRoute.Home.name)
                                }
                                detailsViewModel.getMovieById(movie.id)
                            },
                            onTryAgain = {
                                viewModel.tryAgain()
                            },
                            onDetailsTryAgain = {
                                detailsViewModel.tryAgain()
                            }
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
