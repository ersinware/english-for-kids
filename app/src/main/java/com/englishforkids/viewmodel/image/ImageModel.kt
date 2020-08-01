package com.englishforkids.viewmodel.image

import com.englishforkids.mediautils.Speaker
import com.englishforkids.model.ImageRepository
import com.englishforkids.view.utils.Target
import com.englishforkids.viewmodel.BaseTeachingModel

class ImageModel(target: Target) : BaseTeachingModel<String>() {

    override val repo = ImageRepository(target)

    override val data = repo.imageUrls

    val imageNames = repo.imageNames

    override fun speak(position: Int) {
        Speaker.getInstance().speak(
            imageNames[position]
        )
    }
}