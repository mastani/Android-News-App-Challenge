package app.mastani.news.detail

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import app.mastani.news.detail.ui.ArticleDetail
import app.mastani.news.domain.model.NewsArticle

@Composable
fun DetailScreen(
    navController: NavController = rememberNavController(),
    article: NewsArticle? = null,
    isArchived: Boolean,
    onUserIntent: (DetailIntent) -> Unit
) {
    ArticleDetail(
        article = article ?: return,
        isArchived = isArchived,
        addToArchive = {
            if (isArchived)
                onUserIntent(DetailIntent.RemoveArticleFromArchive(article))
            else
                onUserIntent(DetailIntent.AddArticleToArchive(article))
        }
    )
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
fun DetailScreenPreview() {
    DetailScreen(
        isArchived = false,
        onUserIntent = {}
    )
}