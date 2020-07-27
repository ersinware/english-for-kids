package com.englishforkids.viewmodel.image

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.englishforkids.view.utils.Target

class ImageModelFactory(
    private val target: Target
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(
        modelClass: Class<T>
    ) = ImageModel(target) as T
}