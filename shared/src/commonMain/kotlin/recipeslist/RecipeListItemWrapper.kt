package recipeslist

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

/**
 * Created by "Mohamad Abuzaid" on 25/05/2023.
 * Email: m.abuzaid.ali@gmail.com
 */
@Composable
fun RecipeListItemWrapper(
    child: @Composable () -> Unit,
    scrollDirection: Boolean
) {
    val cameraAnimatable = remember { Animatable(initialValue = 7.0f) }
    val scaleAnimatable = remember { Animatable(initialValue = 0.7f) }
    val rotateXAnimatable = remember { Animatable(initialValue = if (scrollDirection) 60f else -60f) }

    // Observe changes to scrollDirection and update rotateXAnimatable accordingly
    LaunchedEffect(scrollDirection) {
        // Animate from 0 to either 60 or -60
        rotateXAnimatable.animateTo(
            if (scrollDirection) 60f else -60f,
            animationSpec = tween(
                durationMillis = 100,
                easing = CubicBezierEasing(0f, 0.5f, 0.5f, 1f)
            )
        )
        // Animate from either 60 or -60 to 0
        rotateXAnimatable.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 500,
                easing = CubicBezierEasing(0f, 0.5f, 0.5f, 1f)
            )
        )
    }

    // Other animations (camera and scale) remain the same
    LaunchedEffect(Unit) {
        cameraAnimatable.animateTo(
            8.0f,
            animationSpec = tween(
                durationMillis = 500,
                easing = CubicBezierEasing(0f, 0.5f, 0.5f, 1f)
            )
        )
    }

    LaunchedEffect(Unit) {
        scaleAnimatable.animateTo(
            1f,
            animationSpec = tween(
                durationMillis = 700,
                easing = CubicBezierEasing(0f, 0.5f, 0.5f, 1f)
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                scaleX = scaleAnimatable.value
                scaleY = scaleAnimatable.value
                rotationX = rotateXAnimatable.value
                cameraDistance = cameraAnimatable.value
            }
    ) {
        child()
    }
}
