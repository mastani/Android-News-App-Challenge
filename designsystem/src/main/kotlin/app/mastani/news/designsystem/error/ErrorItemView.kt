package app.mastani.news.designsystem.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.mastani.news.designsystem.R
import app.mastani.news.designsystem.theme.Typography

@Composable
fun ErrorItemView(
    modifier: Modifier = Modifier,
    errorMessage: String,
    onRetry: (() -> Unit)? = null
) {
    Box(modifier = modifier) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Row {
                Text(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .align(Alignment.CenterVertically),
                    text = if (errorMessage.isEmpty()) "Network error" else errorMessage,
                    textAlign = TextAlign.Center,
                    style = Typography.titleMedium
                )

                Image(
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(id = R.drawable.ic_warning),
                    contentDescription = "Error Message"
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Button(
                    modifier = Modifier
                        .pointerInput(onRetry) { detectTapGestures { onRetry?.invoke() } }
                        .padding(end = 10.dp),
                    onClick = {
                        onRetry?.invoke()
                    },
                ) {
                    Text(
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .align(Alignment.CenterVertically),
                        text = "Retry",
                        textAlign = TextAlign.Center,
                        style = Typography.titleMedium
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ErrorPreview() {
    ErrorItemView(
        modifier = Modifier.fillMaxSize(),
        errorMessage = "Network issue"
    )
}