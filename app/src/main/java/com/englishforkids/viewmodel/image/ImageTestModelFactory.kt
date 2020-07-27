package com.englishforkids.viewmodel.image

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.englishforkids.view.utils.Target

class ImageTestModelFactory(
    private val target: Target
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(
        modelClass: Class<T>
    ) = ImageTestModel(target) as T
}