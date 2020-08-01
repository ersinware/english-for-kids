package com.englishforkids.mediautils

import android.media.MediaPlayer
import com.englishforkids.view.appContext

class SoundPlayer private constructor() {

    companion object {
        private lateinit var instance: SoundPlayer

        fun initialize() {
            if (::instance.isInitialized)
                return

            instance = SoundPlayer()
        }

        fun getInstance() = instance
    }

    private val player = MediaPlayer()

    private val correctDesc =
        appContext.assets.openFd("sounds/correctSound.mp3")

    private val incorrectDesc =
        appContext.assets.openFd("sounds/incorrectSound.mp3")

    fun startSound(correct: Boolean) =
        player.run {
            reset()

            val desc = if (correct) correctDesc
            else incorrectDesc

            setDataSource(
                desc.fileDescriptor,
                desc.startOffset,
                desc.length
            )
            prepare()
            start()
        }
}