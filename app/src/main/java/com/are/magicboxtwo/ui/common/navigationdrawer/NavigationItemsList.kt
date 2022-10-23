package com.are.magicboxtwo.ui.common.navigationdrawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable

enum class NavigationItemsList {
    Home,
    Settings,
    Help
}

@Composable
fun getNavigationItemsList(): List<MagicBoxNavigationItem> {
    return listOf(
        MagicBoxNavigationItem(
            id = "home",
            title = NavigationItemsList.Home.name,
            contentDescription = "Home Screen",
            icon = Icons.Default.Home
        ),
        MagicBoxNavigationItem(
            id = "settings",
            title = NavigationItemsList.Settings.name,
            contentDescription = "Settings Screen",
            icon = Icons.Default.Settings
        ),
        MagicBoxNavigationItem(
            id = "help",
            title = NavigationItemsList.Help.name,
            contentDescription = "Get Help",
            icon = Icons.Default.Info
        )
    )
}