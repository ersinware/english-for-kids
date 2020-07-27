package com.englishforkids.view.utils

import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.englishforkids.view.appContext
import com.google.android.material.card.MaterialCardView

@BindingAdapter("backgroundColorId")
fun setBackground(
    view: View,
    colorId: Int
) {
    view.background = ColorDrawable(colorId)
}

@BindingAdapter("cardBackgroundColorId")
fun setCardBackgroundTint(
    card: MaterialCardView,
    cardBackgroundColorId: Int
) {
    card.backgroundTintList =
        ColorStateList.valueOf(
            ResourcesCompat.getColor(
                card.resources,
                cardBackgroundColorId,
                appContext.theme
            )
        )
}

@BindingAdapter("buttonColorId")
fun setButtonTint(
    btn: ImageButton,
    buttonColorId: Int
) = btn.setColorFilter(buttonColorId)

@BindingAdapter("imageId")
fun setImageResource(
    img: ImageView,
    imageId: Int
) = Glide
    .with(appContext)
    .load(imageId)
    .into(img)

@BindingAdapter("pagerListener")
fun setPagerListener(
    pager: ViewPager2,
    listener: ViewPager2.OnPageChangeCallback
) = pager.registerOnPageChangeCallback(listener)
