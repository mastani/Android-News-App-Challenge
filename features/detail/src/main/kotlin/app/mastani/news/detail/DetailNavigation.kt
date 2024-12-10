package app.mastani.news.detail

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.mastani.news.domain.model.NewsArticle

fun NavGraphBuilder.detailScreen(
    navController: NavController
) {
    composable<NewsArticle> {
        val viewModel = hiltViewModel<DetailViewModel>()
        val isArchived by viewModel.isArchived.collectAsStateWithLifecycle()

        DetailScreen(
            navController = navController,
            article = viewModel.article,
            isArchived = isArchived,
            onUserIntent = viewModel::onIntent
        )
    }
}