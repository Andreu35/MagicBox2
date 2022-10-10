package com.are.magicboxtwo.ui.common.topappbar

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.are.magicboxtwo.R
import com.are.magicboxtwo.ui.theme.MBTheme

enum class ActionType {
    DRAWER,
    SEARCH,
    LIGHT_DARK
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MagicBoxTopAppBar(
    onActionClick: (type: ActionType) -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = MBTheme.colors.primary
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    onActionClick(ActionType.DRAWER)
                },
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