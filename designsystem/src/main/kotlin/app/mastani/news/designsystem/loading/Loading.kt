package app.mastani.news.designsystem.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun LazyListScope.loading(modifier: Modifier = Modifier) {
    item {
        Box(modifier = modifier.fillMaxWidth()) {
            CircleShapeIndicator(
                modifier = Modifier.size(40.dp).align(Alignment.Center),
            )
        }
    }
}