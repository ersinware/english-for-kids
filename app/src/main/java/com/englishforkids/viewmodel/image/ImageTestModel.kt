package com.englishforkids.viewmodel.image

import androidx.lifecycle.ViewModel
import com.englishforkids.R
import com.englishforkids.view.utils.Target
import com.englishforkids.viewmodel.BaseTestModel

class ImageTestModel(
    private val target: Target
) : ViewModel(), BaseTestModel<Int> {

    override val data: Array<Int>

    private val imageNames: Array<String>

    init {
        // target'a göre
        data = arrayOf(
            R.drawable.homepage_animals,
            R.drawable.homepage_fruits,
            R.drawable.homepage_objects
        )

        imageNames = arrayOf(
            "First Name",
            "Second Name",
            "Third Name"
        )
        loadModel()
    }

    var firstImgId: Int? = null

    var secondImgId: Int? = null

    var thirdImgId: Int? = null

    var fourthImgId: Int? = null

    var selectedImgId: Int? = null

    fun loadModel() {
        // data'dan al
        firstImgId = R.drawable.homepage_alphabet
        secondImgId = R.drawable.homepage_animals
        thirdImgId = R.drawable.homepage_fruits
        fourthImgId = R.drawable.homepage_objects

        selectedImgId = null
    }

    override fun speak() {
        // imageNames'ten selectedImgId'nin index'ine göre söylet
    }
}