package app.mastani.news.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import app.mastani.news.designsystem.component.Article
import app.mastani.news.designsystem.component.Headline
import app.mastani.news.designsystem.component.QueryFilter
import app.mastani.news.designsystem.dummy.dummyFocusableView
import app.mastani.news.designsystem.error.error
import app.mastani.news.designsystem.loading.loading
import app.mastani.news.designsystem.theme.Typography
import app.mastani.news.domain.model.NewsArticle
import kotlin.random.Random

@Composable
fun HomeScreen(
    navController: NavController = rememberNavController(),
    headlineState: HeadlinesState,
    newsState: NewsState,
    onUserIntent: (HomeIntent) -> Unit
) {
    var retryKey by remember { mutableIntStateOf(0) }

    val newsQueries = remember {
        listOf(
            "Microsoft",
            "Apple",
            "Google",
            "Tesla"
        )
    }

    LaunchedEffect(key1 = retryKey) {
        onUserIntent(HomeIntent.FetchHeadlines)
    }

    var selectedQuery by remember { mutableStateOf(newsQueries.first()) }
    LaunchedEffect(key1 = selectedQuery, key2 = retryKey) {
        onUserIntent(HomeIntent.FetchNews(selectedQuery))
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = rememberLazyListState()
    ) {
        item {
            QueryFilter(
                modifier = Modifier.padding(horizontal = 12.dp),
                queries = newsQueries,
                selectedQuery = selectedQuery,
                onQueryChanged = { query ->
                    selectedQuery = query
                }
            )
        }

        when (headlineState) {
            HeadlinesState.Loading -> loading()
            is HeadlinesState.Success -> headlines(
                navController = navController,
                articles = headlineState.articles
            )

            is HeadlinesState.Error -> {
                if (newsState is NewsState.Success && newsState.isFromCache) {
                    dummyFocusableView()
                } else {
                    error(
                        errorMessage = headlineState.error ?: "",
                        onRetry = {
                            retryKey = Random.nextInt()
                        }
                    )
                }
            }
        }

        when (newsState) {
            NewsState.Loading -> loading()
            is NewsState.Success -> newsArticle(
                navController = navController,
                articles = newsState.articles,
                selectedQuery = selectedQuery
            )
            is NewsState.Error -> error(
                errorMessage = newsState.error ?: "",
                onRetry = {
                    retryKey = Random.nextInt()
                }
            )
        }
    }
}

fun LazyListScope.headlines(
    navController: NavController,
    articles: List<NewsArticle>
) {
    item {
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(vertical = 6.dp),
            text = stringResource(R.string.headline),
            style = Typography.headlineSmall
        )
    }

    item {
        LazyRow(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(articles) { article ->
                Headline(
                    article = article,
                    onArticleClicked = {
                        navController.navigate(article)
                    }
                )
            }
        }
    }
}

fun LazyListScope.newsArticle(
    navController: NavController,
    articles: List<NewsArticle>,
    selectedQuery: String
) {
    item {
        Text(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(vertical = 6.dp),
            text = "About: $selectedQuery",
            style = Typography.headlineSmall
        )
    }

    items(articles) { article ->
        Article(
            article = article,
            onArticleClicked = {
                navController.navigate(article)
            }
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        newsState = NewsState.Success(articles = listOf(), isFromCache = false),
        headlineState = HeadlinesState.Success(articles = listOf()),
        onUserIntent = {}
    )
}