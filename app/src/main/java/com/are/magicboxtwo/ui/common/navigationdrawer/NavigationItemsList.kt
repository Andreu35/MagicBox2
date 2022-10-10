package com.are.magicboxtwo.ui.common.navigationdrawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable

@Composable
fun getNavigationItemsList(): List<MagicBoxNavigationItem> {
    return listOf(
        MagicBoxNavigationItem(
            id = "home",
            title = "Home",
            contentDescription = "Home Screen",
            icon = Icons.Default.Home
        ),
        MagicBoxNavigationItem(
            id = "settings",
            title = "Settings",
            contentDescription = "Settings Screen",
            icon = Icons.Default.Settings
        ),
        MagicBoxNavigationItem(
            id = "help",
            title = "Help",
            contentDescription = "Get Help",
            icon = Icons.Default.Info
        )
    )
}