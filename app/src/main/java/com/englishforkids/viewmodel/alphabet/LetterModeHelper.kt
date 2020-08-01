package com.englishforkids.viewmodel.alphabet

import android.content.Context
import androidx.core.content.edit
import com.englishforkids.R
import com.englishforkids.view.appContext

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

    fun getLetterModeByClicked(mode: Int) =
        when (mode) {
            0 -> LetterMode.MODE_CAPITAL
            1 -> LetterMode.MODE_SMALL
            else -> LetterMode.MODE_TOGETHER
        }

    fun findClickedMode(itemId: Int) =
        when (itemId) {
            R.id.itemCapitalLetters -> 0
            R.id.itemSmallLetters -> 1
            else -> 2
        }

    fun editLetterMode(mode: Int) {
        appContext.getSharedPreferences(
            "prefs",
            Context.MODE_PRIVATE
        ).edit {
            putInt("letterMode", mode)
        }
    }
}