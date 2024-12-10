package app.mastani.news.archive

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import app.mastani.news.designsystem.component.Article
import app.mastani.news.designsystem.theme.Typography
import app.mastani.news.domain.model.NewsArticle

@Composable
fun ArchiveScreen(
    navController: NavController = rememberNavController(),
    archiveState: ArchiveState,
    onUserIntent: (ArchiveIntent) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        onUserIntent(ArchiveIntent.FetchArchivedArticles)
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = rememberLazyListState()
    ) {
        if (archiveState is ArchiveState.Success) {
            if (archiveState.articles.isEmpty()) {
                emptyArchive()
            } else {
                archivedArticle(
                    navController = navController,
                    articles = archiveState.articles
                )
            }
        }
    }
}

fun LazyListScope.emptyArchive() {
    item {
        Column(
            modifier = Modifier.fillMaxSize().padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(128.dp),
                painter = painterResource(R.drawable.ic_sad),
                contentDescription = "Sad Character"
            )

            Text(
                modifier = Modifier,
                text = stringResource(R.string.archive_is_empty),
                style = Typography.labelLarge
            )
        }
    }
}

fun LazyListScope.archivedArticle(
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
fun ArchiveScreenPreview() {
    ArchiveScreen(
        archiveState = ArchiveState.Success(articles = listOf()),
        onUserIntent = {}
    )
}