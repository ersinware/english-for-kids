package com.englishforkids.view.utils

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import com.englishforkids.R
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun View.awaitAdd() =
    suspendCoroutine<Unit> { continuation ->
        addOnLayoutChangeListener(
            object : View.OnLayoutChangeListener {
                override fun onLayoutChange(
                    v: View?,
                    left: Int,
                    top: Int,
                    right: Int,
                    bottom: Int,
                    oldLeft: Int,
                    oldTop: Int,
                    oldRight: Int,
                    oldBottom: Int
                ) {
                    this@awaitAdd.removeOnLayoutChangeListener(this)
                    continuation.resume(Unit)
                }
            })
    }

fun ViewGroup.addCorrectImage(
    inflater: LayoutInflater,
    background: Drawable
) = addView(
    inflater.inflate(
        R.layout.baby_correct_image,
        this,
        false
    ).apply {
        this.background = background
    }
)

fun ViewGroup.addIncorrectImage(
    inflater: LayoutInflater,
    background: Drawable
) = addView(
    inflater.inflate(
        R.layout.baby_incorrect_image,
        this,
        false
    ).apply {
        this.background = background
    }
)

fun ViewGroup.setVisibilityChildren(
    visibility: Int
) {
    for (child in children)
        child.visibility = visibility
}

fun ViewGroup.setClickableChildren(
    clickable: Boolean
) {
    for (child in children)
        child.isClickable = clickable
}

fun ViewGroup.removeLastChildrenOfChildren() {
    for (child in children)
        (child as? ViewGroup)?.let {
            if (it.childCount > 1)
                for (i in it.childCount - 1 downTo 1)
                    it.removeViewAt(i)
        }
}

fun ViewGroup.removeChildrenOfChildren() {
    for (child in children)
        (child as? ViewGroup)?.let {
            for (i in it.childCount - 1 downTo 0)
                it.removeViewAt(i)
        }
}
