package com.englishforkids.viewmodel.alphabet

import com.englishforkids.mediautils.Speaker
import com.englishforkids.model.AlphabetRepository
import com.englishforkids.viewmodel.BaseTeachingModel

class AlphabetModel : BaseTeachingModel<String>(), AlphabetBaseModel {

    override val repo = AlphabetRepository

    override var letterMode = LetterModeHelper.getLetterMode()
        set(value) {
            if (field == value)
                return

            field = value
            onLetterModeChange()
        }

    override var data = getDataByLetterMode()

    override fun speak(position: Int) {
        Speaker.getInstance().speak(
            data[position][0].toString()
        )
    }
}