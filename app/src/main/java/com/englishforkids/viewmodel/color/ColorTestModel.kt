package com.englishforkids.viewmodel.color

import androidx.lifecycle.ViewModel
import com.englishforkids.R
import com.englishforkids.viewmodel.BaseTestModel

class ColorTestModel : ViewModel(), BaseTestModel<Int> {

    override val data = arrayOf(1)

    val colorNames = arrayOf(
        "First Name",
        "Second Name",
        "Third Name"
    )

    init {
        loadModel()
    }

    var firstColorId: Int? = null

    var secondColorId: Int? = null

    var thirdColorId: Int? = null

    var fourthColorId: Int? = null

    var selectedColorId: Int? = null

    fun loadModel() {
        // data'ya göre
        firstColorId = R.color.colorAnimalsBackground
        secondColorId = R.color.colorFruitsBackground
        thirdColorId = R.color.colorObjectsBackground
        fourthColorId = R.color.colorNumbersBackground

        selectedColorId = null
    }

    override fun speak() {
        // colorNames'ten selectedColorId'nin index'ine göre söylet
    }
}