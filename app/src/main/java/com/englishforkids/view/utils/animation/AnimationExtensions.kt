package com.englishforkids.view.utils.animation

import android.animation.ObjectAnimator
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.englishforkids.R
import com.google.android.material.transition.MaterialSharedAxis
import com.google.android.material.transition.VisibilityAnimatorProvider
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

fun getFirstTransition(forward: Boolean) =
    MaterialSharedAxis(
        MaterialSharedAxis.Z,
        forward
    ).apply {
        duration = FIRST_TRANSITION_DURATION
        secondaryAnimatorProvider =
            getSecondAnimatorProvider(FIRST_TRANSITION_DURATION)
    }

fun getTransition(forward: Boolean) =
    MaterialSharedAxis(
        MaterialSharedAxis.Z,
        forward
    ).apply {
        duration = TRANSITION_DURATION
        secondaryAnimatorProvider =
            getSecondAnimatorProvider(TRANSITION_DURATION)
    }

private fun getSecondAnimatorProvider(
    duration: Long
) = object : VisibilityAnimatorProvider {
    override fun createAppear(
        sceneRoot: ViewGroup,
        view: View
    ) = ObjectAnimator.ofFloat(
        view,
        "alpha",
        0f,
        1f
    ).apply {
        this.duration = duration
    }

    override fun createDisappear(
        sceneRoot: ViewGroup,
        view: View
    ) = ObjectAnimator.ofFloat(
        view,
        "alpha",
        1f,
        0f
    ).apply {
        this.duration = duration
    }
}

suspend fun Animation.awaitEnd() =
    suspendCoroutine<Unit> { continuation ->
        setAnimationListener(
            object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {

                }

                override fun onAnimationRepeat(animation: Animation?) {

                }

                override fun onAnimationEnd(animation: Animation?) {
                    this@awaitEnd.setAnimationListener(null)
                    continuation.resume(Unit)
                }
            }
        )
    }

suspend fun View.awaitFade(
    forward: Boolean
) = suspendCoroutine<Unit> { continuation ->
    // it is not always false
    if (this == null)
        return@suspendCoroutine

    startAnimation(
        AnimationUtils.loadAnimation(
            context,
            if (forward) R.anim.anim_fade_in
            else R.anim.anim_fade_out
        ).apply {
            setAnimationListener(
                object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {
                        visibility = if (forward) View.VISIBLE
                        else View.INVISIBLE
                    }

                    override fun onAnimationRepeat(animation: Animation?) {

                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        setAnimationListener(null)
                        continuation.resume(Unit)
                    }
                }
            )
        }
    )
}

fun View.fadeOut() =
    AlphaAnimation(
        1f,
        0f
    ).apply {
        duration = FADE_OUT_DURATION
    }.run {
        startAnimation(this)
        this@fadeOut.visibility = View.INVISIBLE
    }