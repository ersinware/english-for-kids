package com.englishforkids.viewmodel.color

import androidx.lifecycle.ViewModel
import com.englishforkids.R
import com.englishforkids.viewmodel.BaseTeachingModel

class ColorModel : ViewModel(), BaseTeachingModel<Int> {

    override val data = arrayOf(
        R.color.colorAccent,
        R.color.colorAlphabetButtonTint,
        R.color.colorFruitsBackground
    )

    val colorNames = arrayOf(
        "First Name",
        "Second Name",
        "Third Name"
    )

    override fun speak(position: Int) {
        // colorNames'ten al
    }

}