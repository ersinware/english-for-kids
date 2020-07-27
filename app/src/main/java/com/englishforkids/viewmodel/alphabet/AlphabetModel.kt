package com.englishforkids.viewmodel.alphabet

import com.englishforkids.view.utils.lettermode.LetterMode
import com.englishforkids.viewmodel.BaseTeachingModel

class AlphabetModel(
    letterMode: LetterMode
) : AlphabetBaseModel(letterMode), BaseTeachingModel<String> {

    override lateinit var data: Array<String>

    override fun onLetterModeChange() {
        // letterMode'a göre ata
        data = arrayOf(
            "Aa",
            "Bb",
            "Cc"
        )
    }

    override fun speak(position: Int) {
        // data'dan al
    }
}