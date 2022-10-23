package com.are.magicboxtwo.ui.common.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.are.magicboxtwo.data.model.Movie
import com.are.magicboxtwo.ui.features.detail.components.DetailsScreen
import com.are.magicboxtwo.ui.features.detail.state.DetailsUIStateResponse
import com.are.magicboxtwo.ui.features.home.components.HomeScreen
import com.are.magicboxtwo.ui.features.home.state.HomeUIStateResponse

@Composable
fun MagicBoxNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues,
    homeUIStateResponse: HomeUIStateResponse,
    detailsUIStateResponse: DetailsUIStateResponse,
    onItemClick: (movie: Movie) -> Unit,
    onTryAgain: () -> Unit,
    onDetailsTryAgain: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = MagicBoxNavigationRoute.Home.name
    ) {
        composable(
            route = MagicBoxNavigationRoute.Home.name
        ) {
            HomeScreen(
                modifier = Modifier
                    .padding(paddingValues),
                homeUIStateResponse = homeUIStateResponse,
                onItemClick = onItemClick,
                onTryAgain = onTryAgain
            )
        }
        composable(
            route = "${MagicBoxNavigationRoute.Detail.name}/{${MagicBoxNavigationRoute.MovieId.name}}",
            arguments = listOf(
                navArgument(MagicBoxNavigationRoute.MovieId.name) {
                    type = NavType.IntType
                }
            )
        ) {
            DetailsScreen(
                modifier = Modifier
                    .padding(paddingValues),
                detailsUIStateResponse = detailsUIStateResponse,
                onTryAgain = onDetailsTryAgain
            )
        }
        composable(
            route = MagicBoxNavigationRoute.Settings.name
        ) {

        }
        composable(
            route = MagicBoxNavigationRoute.Help.name
        ) {

        }
    }
}