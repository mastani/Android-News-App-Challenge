package app.mastani.news.home

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.homeScreen(
    route: String,
    navController: NavController
) {
    composable(route = route) {
        val viewModel = hiltViewModel<HomeViewModel>()
        val headlineState by viewModel.headlineState.collectAsStateWithLifecycle()
        val homeState by viewModel.newsState.collectAsStateWithLifecycle()

        HomeScreen(
            navController = navController,
            headlineState = headlineState,
            newsState = homeState,
            onUserIntent = viewModel::onIntent
        )
    }
}