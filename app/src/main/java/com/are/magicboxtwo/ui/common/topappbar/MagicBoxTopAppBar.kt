package com.are.magicboxtwo.ui.common.topappbar

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.are.magicboxtwo.R
import com.are.magicboxtwo.ui.features.home.state.HomeUIState
import com.are.magicboxtwo.ui.theme.MBTheme

enum class ActionType {
    SEARCH,
    LIGHT_DARK
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MagicBoxTopAppBar(
    uiState: HomeUIState = HomeUIState(),
    onDrawerClick: () -> Unit,
    onActionClick: (type: ActionType) -> Unit,
    onSearch: (query: String) -> Unit,
    onSearchClose: () -> Unit
) {
    when (uiState.searchState) {
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
                        text = stringResource(id = R.string.app_name),
                        color = MBTheme.colors.primary
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onDrawerClick,
                        content = {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Toggle Drawer"
                            )
                        }
                    )
                },
                actions = {
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
            )
        }
    }
}