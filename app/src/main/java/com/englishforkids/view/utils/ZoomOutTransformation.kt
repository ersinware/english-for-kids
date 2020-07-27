package com.englishforkids.view.utils

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs
import kotlin.math.max

class ZoomOutTransformation : ViewPager2.PageTransformer {

    private val minScale = 0.65f
    private val minAlpha = 0.3f

    override fun transformPage(page: View, position: Float) =
        page.run {
            when {
                position < -1 -> {  // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    alpha = 0f;

                }
                position <= 1 -> { // [-1,1]
                    scaleX = max(minScale, 1 - abs(position));
                    scaleY = max(minScale, 1 - abs(position));
                    alpha = max(minAlpha, 1 - abs(position));

                }
                else -> {  // (1,+Infinity]
                    // This page is way off-screen to the right.
                    alpha = 0f;

                }
            }
        }
}