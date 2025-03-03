package com.codandotv.streamplayerapp.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun LottieComponent(
    jsonString: String,
    modifier: Modifier,
    onAnimationFinished: () -> Unit
) = Unit