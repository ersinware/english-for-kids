package com.englishforkids.viewmodel.alphabet

import com.englishforkids.view.utils.lettermode.LetterMode
import com.englishforkids.viewmodel.BaseTestModel

class AlphabetTestModel(
    letterMode: LetterMode
) : AlphabetBaseModel(letterMode), BaseTestModel<String> {

    override lateinit var data: Array<String>

    override fun onLetterModeChange() {
        // letterMode'a göre
        data = arrayOf(
            "Aa",
            "Bb",
            "Cc"
        )
        loadModel()
    }

    lateinit var firstLetter: String

    lateinit var secondLetter: String

    lateinit var thirdLetter: String

    lateinit var fourthLetter: String

    lateinit var selectedLetter: String

    fun loadModel() {
        // data'dan al
        firstLetter = "a"
        secondLetter = "f"
        thirdLetter = "p"
        fourthLetter = "z"

        selectedLetter = "a"
    }

    override fun speak() {
        // selectedLetter'ı söylet
    }
}