package com.englishforkids.viewmodel.image

import com.englishforkids.mediautils.Speaker
import com.englishforkids.model.ImageRepository
import com.englishforkids.view.utils.Target
import com.englishforkids.viewmodel.BaseTestModel
import com.englishforkids.viewmodel.SpeakerModel

class ImageTestModel(
    target: Target
) : BaseTestModel<String>(), SpeakerModel<String> {

    override val repo = ImageRepository(target)

    override val data = repo.imageUrls

    private val imageNames = repo.imageNames

    init {
        loadElements()
    }

    override fun speak() {
        Speaker.getInstance().speak(
            imageNames[findIndex(selected!!)]
        )
    }
}