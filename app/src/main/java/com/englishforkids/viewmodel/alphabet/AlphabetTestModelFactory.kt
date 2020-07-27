package com.englishforkids.viewmodel.alphabet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.englishforkids.view.utils.lettermode.LetterMode

class AlphabetTestModelFactory(
    private val letterMode: LetterMode
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(
        modelClass: Class<T>
    ) = AlphabetTestModel(letterMode) as T
}