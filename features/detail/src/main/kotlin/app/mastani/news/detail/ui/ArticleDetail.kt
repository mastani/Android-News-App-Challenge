package app.mastani.news.detail.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.mastani.news.common.toHumanReadableDate
import app.mastani.news.designsystem.theme.Typography
import app.mastani.news.detail.R
import app.mastani.news.domain.model.NewsArticle
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ArticleDetail(
    article: NewsArticle,
    isArchived: Boolean,
    addToArchive: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier,
            text = article.title,
            style = Typography.headlineSmall,
            color = Color.DarkGray
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Source: ${article.source}",
                style = Typography.titleSmall,
                color = Color.Gray
            )

            Row(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .clickable { addToArchive() }
            ) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    painter = if (!isArchived)
                        painterResource(id = R.drawable.ic_add_archive)
                    else
                        painterResource(id = R.drawable.ic_archived),
                    contentDescription = "Archive"
                )

                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = if (!isArchived)
                        stringResource(R.string.add_to_archive)
                    else
                        stringResource(R.string.remove_from_archive),
                    style = Typography.titleSmall
                )
            }
        }

        if (article.imageUrl != null) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(article.imageUrl)
                    .crossfade(true)
                    .placeholder(app.mastani.news.designsystem.R.drawable.placeholder)
                    .error(app.mastani.news.designsystem.R.drawable.placeholder)
                    .build(),
                contentDescription = "News item image",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp)
                    .height(250.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = article.content,
            style = Typography.bodyLarge
        )

        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = "Published: ${article.publishedAt.toHumanReadableDate()}",
            style = Typography.titleSmall,
            color = Color.Gray
        )
    }
}