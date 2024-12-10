package app.mastani.news.archive

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.archiveScreen(
    route: String,
    navController: NavController
) {
    composable(route = route) {
        val viewModel = hiltViewModel<ArchiveViewModel>()
        val archiveState by viewModel.archiveState.collectAsStateWithLifecycle()

        ArchiveScreen(
            navController = navController,
            archiveState = archiveState,
            onUserIntent = viewModel::onIntent
        )
    }
}