package com.englishforkids.view.utils.animation

import android.view.View
import android.view.animation.ScaleAnimation

class ButtonClickAnimator {

    private val anim = ScaleAnimation(
        1f,
        0.8f,
        1f,
        0.8f,
        ScaleAnimation.RELATIVE_TO_SELF,
        0.5f,
        ScaleAnimation.RELATIVE_TO_SELF,
        0.5f
    ).apply {
        duration = CARD_CLICK_ANIMATION_DURATION
        repeatCount = 1
        repeatMode = ScaleAnimation.REVERSE
    }

    fun start(view: View) = view.startAnimation(anim)
}