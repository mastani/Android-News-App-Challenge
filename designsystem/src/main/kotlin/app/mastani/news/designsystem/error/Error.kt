package app.mastani.news.designsystem.error

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier

fun LazyListScope.error(
    modifier: Modifier = Modifier,
    errorMessage: String,
    onRetry: (() -> Unit)? = null
) {
    item {
        ErrorItemView(
            modifier = modifier.fillMaxWidth(),
            errorMessage = errorMessage,
            onRetry = onRetry
        )
    }
}