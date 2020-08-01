package com.englishforkids.model

import androidx.core.content.res.ResourcesCompat
import com.englishforkids.R
import com.englishforkids.view.appContext

object ConfettiRepository {

    val colors = listOf(
        getColor(R.color.colorConfettiOne),
        getColor(R.color.colorConfettiTwo),
        getColor(R.color.colorConfettiThree),
        getColor(R.color.colorConfettiFour),
        getColor(R.color.colorConfettiFive),
        getColor(R.color.colorConfettiSix),
        getColor(R.color.colorConfettiSeven),
        getColor(R.color.colorConfettiEight),
        getColor(R.color.colorConfettiNine),
        getColor(R.color.colorConfettiTen),
        getColor(R.color.colorConfettiEleven),
        getColor(R.color.colorConfettiTwelve),
        getColor(R.color.colorConfettiThirteen),
        getColor(R.color.colorConfettiFourteen),
        getColor(R.color.colorConfettiFifteen)
    )

    private fun getColor(id: Int) =
        ResourcesCompat.getColor(
            appContext.resources,
            id,
            appContext.theme
        )
}