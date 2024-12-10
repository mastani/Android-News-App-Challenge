package app.mastani.news.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import app.mastani.news.common.toHumanReadableDate
import app.mastani.news.designsystem.R
import app.mastani.news.designsystem.theme.Typography
import app.mastani.news.domain.model.NewsArticle
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun Article(
    article: NewsArticle,
    onArticleClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onArticleClicked() }
            .padding(12.dp)
            .border(
                border = BorderStroke(width = 1.dp, color = Color.Black.copy(alpha = 0.4f)),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .height(200.dp)
        ) {
            if (article.imageUrl == null) {
                Box(modifier = Modifier.background(color = Color.Green))
            } else {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(article.imageUrl)
                        .crossfade(true)
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.placeholder)
                        .build(),
                    contentDescription = "News item image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .background(Color.Gray.copy(alpha = 0.1f))
                )
            }
        }

        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                modifier = Modifier,
                text = article.title,
                style = Typography.titleMedium
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier,
                    text = article.source,
                    style = Typography.labelLarge
                )

                Text(
                    modifier = Modifier,
                    text = article.publishedAt.toHumanReadableDate(),
                    style = Typography.labelLarge
                )
            }
        }
    }
}