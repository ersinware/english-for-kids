package com.englishforkids.viewmodel.alphabet

import com.englishforkids.mediautils.Speaker
import com.englishforkids.model.AlphabetRepository
import com.englishforkids.viewmodel.BaseTestModel

class AlphabetTestModel : BaseTestModel<String>(), AlphabetBaseModel {

    override val repo = AlphabetRepository

    override var letterMode = LetterModeHelper.getLetterMode()
        set(value) {
            if (field == value)
                return

            field = value
            onLetterModeChange()
        }

    override var data: Array<String> = getDataByLetterMode()

    override fun onLetterModeChange() {
        super.onLetterModeChange()
        loadElements()
    }

    init {
        loadElements()
    }

    override fun speak() {
        Speaker.getInstance().speak(
            selected!![0].toString()
        )
    }
}