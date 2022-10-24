package com.are.magicboxtwo.ui.common.topappbar

import androidx.compose.animation.*
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.are.magicboxtwo.R
import com.are.magicboxtwo.ui.common.navigation.MagicBoxNavigationRoute
import com.are.magicboxtwo.ui.features.home.state.HomeUIState
import com.are.magicboxtwo.ui.theme.MBTheme

enum class ActionType {
    SEARCH,
    LIGHT_DARK
}

internal val SlideAnimationSpec: FiniteAnimationSpec<IntOffset> =
    TweenSpec(300, 0, LinearEasing)

internal val FadeAnimationSpec: FiniteAnimationSpec<Float> =
    TweenSpec(300, 0, LinearEasing)

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalAnimationApi::class
)
@Composable
fun MagicBoxTopAppBar(
    navController: NavHostController,
    uiState: HomeUIState = HomeUIState(),
    onDrawerClick: () -> Unit,
    onBackClick: () -> Unit,
    onActionClick: (type: ActionType) -> Unit,
    onSearch: (query: String) -> Unit,
    onSearchClose: () -> Unit
) {
    val currentRoute = navController
        .currentBackStackEntryFlow
        .collectAsState(initial = navController.currentBackStackEntry)

    val showBackButton = when (currentRoute.value?.destination?.route) {
        MagicBoxNavigationRoute.Home.name -> false
        else -> true
    }

    AnimatedContent(
        modifier = Modifier
            .fillMaxWidth(),
        targetState = uiState.searchState,
        transitionSpec = {
            slideInHorizontally(
                animationSpec = SlideAnimationSpec,
                initialOffsetX = {
                    if (uiState.searchState == SearchState.Searching) it else -it
                }
            ) + fadeIn(
                animationSpec = FadeAnimationSpec
            ) with slideOutHorizontally(
                animationSpec = SlideAnimationSpec,
                targetOffsetX = {
                    if (uiState.searchState == SearchState.Searching) -it else it
                }
            ) + fadeOut(
                animationSpec = FadeAnimationSpec
            )
        }
    ) { state ->
        when (state) {
            SearchState.Searching -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    contentAlignment = Alignment.Center
                ) {
                    SearchAppBar(
                        onSearch = {
                            onSearch(it)
                        },
                        onClear = onSearchClose
                    )
                }
            }
            SearchState.Closed -> {
                TopAppBar(
                    modifier = Modifier
                        .background(
                            color = MBTheme.colors.onPrimary
                        ),
                    title = {
                        Text(
                            text = if (showBackButton) uiState.appBarTitle else stringResource(id = R.string.app_name),
                            color = MBTheme.colors.primary
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = if (showBackButton) onBackClick else onDrawerClick,
                            content = {
                                Icon(
                                    imageVector = if (showBackButton) Icons.Default.ArrowBack else Icons.Default.Menu,
                                    contentDescription = "Toggle Drawer"
                                )
                            }
                        )
                    },
                    actions = {
                        if (!showBackButton) {
                            IconButton(
                                onClick = {
                                    onActionClick(ActionType.SEARCH)
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
                                    onActionClick(ActionType.LIGHT_DARK)
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
                    }
                )
            }
        }
    }
}