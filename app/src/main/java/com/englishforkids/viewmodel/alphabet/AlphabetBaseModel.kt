package com.englishforkids.viewmodel.alphabet

import androidx.lifecycle.ViewModel
import com.englishforkids.view.utils.lettermode.LetterMode

abstract class AlphabetBaseModel(
    letterMode: LetterMode
) : ViewModel() {

    var letterMode: LetterMode? = null
        set(value) {
            field = value
            onLetterModeChange()
        }

    init {
        this.letterMode = letterMode
    }

    abstract fun onLetterModeChange()
}