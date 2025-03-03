package com.codandotv.streamplayerapp.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
actual fun LottieComponent(
    jsonString: String,
    modifier: Modifier,
    onAnimationFinished: () -> Unit
) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.JsonString(
            jsonString = jsonString
        )
    )
    val logoAnimationState = animateLottieCompositionAsState(
        composition = composition
    )

    LottieAnimation(
        composition = composition,
        progress = { logoAnimationState.progress }
    )

    if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
        onAnimationFinished()
    }
}
