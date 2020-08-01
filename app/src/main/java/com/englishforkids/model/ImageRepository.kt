package com.englishforkids.model

import com.englishforkids.view.appContext
import com.englishforkids.view.utils.Target

class ImageRepository(target: Target) : BaseRepository {

    val imageUrls: Array<String>

    val imageNames: Array<String>

    init {
        val folderName = when (target) {
            Target.TARGET_ANIMALS -> "animals"
            Target.TARGET_FRUITS -> "fruits"
            Target.TARGET_OBJECTS -> "objects"
            else -> "numbers"
        }

        val namesWithPng = appContext.assets.list(folderName)!!

        imageUrls = namesWithPng.map {
            "$folderName/$it"
        }.toTypedArray()

        imageNames = namesWithPng.map {
            if (target == Target.TARGET_NUMBERS)
                it.dropLast(4).drop(1)
            else
                it.dropLast(4)
        }.toTypedArray()
    }
}