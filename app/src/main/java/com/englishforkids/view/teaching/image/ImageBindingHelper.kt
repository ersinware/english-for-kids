package com.englishforkids.view.teaching.image

import androidx.core.content.res.ResourcesCompat
import com.englishforkids.R
import com.englishforkids.view.appContext
import com.englishforkids.view.utils.Target

object ImageBindingHelper {

    fun getBackgroundColorId(
        target: Target
    ) = ResourcesCompat.getColor(
        appContext.resources,
        when (target) {
            Target.TARGET_ANIMALS -> R.color.colorAnimalsBackground
            Target.TARGET_FRUITS -> R.color.colorFruitsBackground
            Target.TARGET_OBJECTS -> R.color.colorObjectsBackground
            else -> R.color.colorNumbersBackground
        },
        appContext.theme
    )

    fun getTitle(
        target: Target
    ) = appContext.getString(
        when (target) {
            Target.TARGET_ANIMALS -> R.string.animalsTitle
            Target.TARGET_FRUITS -> R.string.fruitsTitle
            Target.TARGET_OBJECTS -> R.string.objectsTitle
            else -> R.string.numbersTitle
        }
    )

    fun getButtonColorId(
        target: Target
    ) = ResourcesCompat.getColor(
        appContext.resources,
        when (target) {
            Target.TARGET_ANIMALS -> R.color.colorAnimalsButtonTint
            Target.TARGET_FRUITS -> R.color.colorFruitsButtonTint
            Target.TARGET_OBJECTS -> R.color.colorObjectsButtonTint
            else -> R.color.colorNumbersButtonTint
        },
        appContext.theme
    )

    fun getCardBackgroundColorId(
        target: Target
    ) = when (target) {
        Target.TARGET_ANIMALS -> R.color.colorAnimalsTestCard
        Target.TARGET_FRUITS -> R.color.colorFruitsTestCard
        Target.TARGET_OBJECTS -> R.color.colorObjectsTestCard
        else -> R.color.colorNumbersTestCard
    }
}