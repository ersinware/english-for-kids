package com.englishforkids.viewmodel.image

import androidx.lifecycle.ViewModel
import com.englishforkids.R
import com.englishforkids.view.utils.Target
import com.englishforkids.viewmodel.BaseTeachingModel

class ImageModel(
    private val target: Target
) : ViewModel(), BaseTeachingModel<Int> {

    override val data: Array<Int>

    val imageNames: Array<String>

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
    }

    override fun speak(position: Int) {
        // imageNames'ten al
    }
}