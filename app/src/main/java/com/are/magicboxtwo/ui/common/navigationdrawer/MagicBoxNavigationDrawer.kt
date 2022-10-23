package com.are.magicboxtwo.ui.common.navigationdrawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.are.magicboxtwo.ui.common.navigation.MagicBoxNavigationRoute
import com.are.magicboxtwo.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MagicBoxNavigationDrawer(
    navController: NavController,
    drawerState: DrawerState,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    val currentRoute = navController
        .currentBackStackEntryFlow
        .collectAsState(initial = navController.currentBackStackEntry)

    val enableGestures = when (currentRoute.value?.destination?.route) {
        MagicBoxNavigationRoute.Home.name -> true
        else -> false
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = enableGestures,
        drawerContent = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(270.dp)
                    .background(
                        color = MBTheme.colors.onPrimary
                    ),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                MagicBoxNavigationHeader()
                MagicBoxNavigationBody(
                    items = getNavigationItemsList(),
                    onItemClick = { item ->
                        scope.launch {
                            drawerState.close()
                            when (item.title) {
                                NavigationItemsList.Home.name -> {
                                    if (currentRoute.value?.destination?.route != MagicBoxNavigationRoute.Home.name) {
                                        navController.navigate(MagicBoxNavigationRoute.Home.name)
                                    }
                                }
                                NavigationItemsList.Settings.name -> {
                                    if (currentRoute.value?.destination?.route != MagicBoxNavigationRoute.Settings.name) {
                                        navController.navigate(MagicBoxNavigationRoute.Settings.name)
                                    }
                                }
                                NavigationItemsList.Help.name -> {
                                    if (currentRoute.value?.destination?.route != MagicBoxNavigationRoute.Help.name) {
                                        navController.navigate(MagicBoxNavigationRoute.Help.name)
                                    }
                                }
                            }
                        }
                    }
                )
            }
        }
    ) {
        content()
    }
}

@Composable
fun MagicBoxNavigationHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Red1, Red, Blue, Gray1),
                    tileMode = TileMode.Decal
                )
            )
    )
}

@Composable
fun MagicBoxNavigationBody(
    items: List<MagicBoxNavigationItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp, color = MBTheme.colors.primary),
    onItemClick: (item: MagicBoxNavigationItem) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(item)
                    }
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.contentDescription,
                    tint = MBTheme.colors.primary
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    modifier = Modifier.weight(1f),
                    text = item.title,
                    style = itemTextStyle
                )
            }
        }
    }
}
