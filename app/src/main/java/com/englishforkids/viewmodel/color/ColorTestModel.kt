package com.englishforkids.viewmodel.color

import com.englishforkids.mediautils.Speaker
import com.englishforkids.model.ColorRepository
import com.englishforkids.viewmodel.BaseTestModel
import com.englishforkids.viewmodel.SpeakerModel

class ColorTestModel : BaseTestModel<Int>(), SpeakerModel<Int> {

    override val repo = ColorRepository

    override val data = repo.colorIds

    private val colorNames = repo.colorNames

    init {
        loadElements()
    }

    override fun speak() {
        Speaker.getInstance().speak(
            colorNames[findIndex(selected!!)]
        )
    }
}