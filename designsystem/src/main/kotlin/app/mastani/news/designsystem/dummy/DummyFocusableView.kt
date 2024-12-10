package app.mastani.news.designsystem.dummy

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun LazyListScope.dummyFocusableView() {
    item {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .focusable(),
            text = ""
        )
    }
}