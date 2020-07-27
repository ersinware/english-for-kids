package com.englishforkids.view.utils.lettermode

import android.content.Context
import androidx.core.content.edit
import com.englishforkids.R
import com.englishforkids.view.appContext
import com.englishforkids.viewmodel.alphabet.AlphabetBaseModel

object LetterModeHelper {

    fun getLetterMode() =
        when (getSavedMode()) {
            0 -> LetterMode.MODE_CAPITAL
            1 -> LetterMode.MODE_SMALL
            else -> LetterMode.MODE_TOGETHER
        }

    fun getSavedMode() =
        appContext.getSharedPreferences(
            "prefs",
            Context.MODE_PRIVATE
        ).getInt("letterMode", 1)

    fun changeLetterMode(
        itemId: Int,
        model: AlphabetBaseModel
    ) {
        val mode = findClickedMode(itemId)
        model.letterMode = when (mode) {
            0 -> LetterMode.MODE_CAPITAL
            1 -> LetterMode.MODE_SMALL
            else -> LetterMode.MODE_TOGETHER
        }

        appContext.getSharedPreferences(
            "prefs",
            Context.MODE_PRIVATE
        ).edit {
            putInt("letterMode", mode)
        }
    }

    private fun findClickedMode(itemId: Int) =
        when (itemId) {
            R.id.itemCapitalLetters -> 0
            R.id.itemSmallLetters -> 1
            else -> 2
        }
}