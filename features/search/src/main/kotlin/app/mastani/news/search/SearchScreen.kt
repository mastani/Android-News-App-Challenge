package app.mastani.news.search

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import app.mastani.news.designsystem.component.Article
import app.mastani.news.designsystem.error.error
import app.mastani.news.designsystem.loading.loading
import app.mastani.news.designsystem.theme.Typography
import app.mastani.news.domain.model.NewsArticle

@Composable
fun SearchScreen(
    navController: NavController = rememberNavController(),
    searchState: SearchState,
    onUserIntent: (SearchIntent) -> Unit
) {
    var query by rememberSaveable { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = rememberLazyListState()
    ) {

        item {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(12.dp),
                value = query,
                onValueChange = {
                    query = it
                    onUserIntent(SearchIntent.SearchNews(it))
                },
                label = { Text(stringResource(R.string.search_query)) }
            )
        }

        if (query.isEmpty()) {
            emptyQuery()
        } else {
            when (searchState) {
                SearchState.Loading -> loading()
                is SearchState.Success -> searchResult(
                    navController = navController,
                    articles = searchState.articles
                )

                is SearchState.Error -> error(
                    errorMessage = searchState.error ?: "",
                    onRetry = {
                        onUserIntent(SearchIntent.SearchNews(query))
                    }
                )
            }
        }
    }
}

fun LazyListScope.emptyQuery() {
    item {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(84.dp),
                painter = painterResource(R.drawable.ic_search),
                contentDescription = "Search"
            )

            Text(
                modifier = Modifier.padding(top = 12.dp),
                text = stringResource(R.string.type_anything),
                style = Typography.labelLarge
            )
        }
    }
}

fun LazyListScope.searchResult(
    navController: NavController,
    articles: List<NewsArticle>
) {
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
fun SearchScreenPreview() {
    SearchScreen(
        searchState = SearchState.Success(articles = listOf()),
        onUserIntent = {}
    )
}