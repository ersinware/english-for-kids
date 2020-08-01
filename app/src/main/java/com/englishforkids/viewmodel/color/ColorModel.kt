package com.englishforkids.viewmodel.color

import com.englishforkids.mediautils.Speaker
import com.englishforkids.model.ColorRepository
import com.englishforkids.viewmodel.BaseTeachingModel

class ColorModel : BaseTeachingModel<Int>() {

    override val repo = ColorRepository

    override val data = repo.colorIds

    val colorNames = repo.colorNames

    override fun speak(position: Int) {
        Speaker.getInstance().speak(
            colorNames[position]
        )
    }

}