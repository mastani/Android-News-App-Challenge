package app.mastani.news.search

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.searchScreen(
    route: String,
    navController: NavController
) {
    composable(route = route) {
        val viewModel = hiltViewModel<SearchViewModel>()
        val searchState by viewModel.searchState.collectAsStateWithLifecycle()

        SearchScreen(
            navController = navController,
            searchState = searchState,
            onUserIntent = viewModel::onIntent
        )
    }
}