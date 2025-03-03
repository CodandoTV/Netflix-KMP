package com.codandotv.streamplayerapp.presentation.components

import platform.UIKit.UIView

interface LottieViewProvider {
    fun provideLottieView(lottieAnimationJson: String) : UIView
}