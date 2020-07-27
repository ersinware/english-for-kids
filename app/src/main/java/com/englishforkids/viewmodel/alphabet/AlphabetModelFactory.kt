package com.englishforkids.viewmodel.alphabet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.englishforkids.view.utils.lettermode.LetterMode

class AlphabetModelFactory(
    private val letterMode: LetterMode
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(
        modelClass: Class<T>
    ) = AlphabetModel(letterMode) as T
}