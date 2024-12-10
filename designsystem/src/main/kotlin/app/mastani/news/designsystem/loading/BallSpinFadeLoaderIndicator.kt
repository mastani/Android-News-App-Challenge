package app.mastani.news.designsystem.loading

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun BallSpinFadeLoaderIndicator(
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    animationDuration: Int = 800,
    circleDelay: Long = 100L,
    radius: Float = 40f,
    ballCount: Int = 8,
    ballRadius: Float = 6f
) {
    val angleStep = 360f / ballCount

    val animationValues = (1..ballCount).map { index ->
        var animatedValue: Float by remember { mutableFloatStateOf(0f) }
        LaunchedEffect(key1 = Unit) {
            delay(circleDelay * index)

            animate(
                initialValue = 0.2f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = animationDuration),
                    repeatMode = RepeatMode.Reverse,
                )
            ) { value, _ -> animatedValue = value }
        }

        animatedValue
    }

    Canvas(
        modifier = modifier
    ) {
        val center = Offset(size.width / 2, size.height / 2)
        for (index in 0 until ballCount) {
            val angle = index * angleStep
            val x = center.x + radius * cos(Math.toRadians(angle.toDouble())).toFloat()
            val y = center.y + radius * sin(Math.toRadians(angle.toDouble())).toFloat()
            drawCircle(
                color = color,
                radius = ballRadius * animationValues[index], // Apply the scale
                center = Offset(x, y)
            )
        }
    }
}