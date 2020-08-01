package com.englishforkids.mediautils

import android.speech.tts.TextToSpeech
import com.englishforkids.view.appContext
import java.util.*

class Speaker private constructor() {

    companion object {
        private lateinit var instance: Speaker

        fun initialize() {
            if (::instance.isInitialized)
                return

            instance = Speaker()
        }

        fun getInstance() = instance
    }

    private val speaker = TextToSpeech(
        appContext,
        TextToSpeech.OnInitListener { status ->
            onSpeakerInit(status)
        }
    )

    private fun onSpeakerInit(status: Int) {
        if (status == TextToSpeech.SUCCESS)
            speaker.language = Locale.US
    }

    fun speak(text: String) {
        if (speaker.isSpeaking)
            speaker.stop()

        speaker.speak(
            text,
            TextToSpeech.QUEUE_FLUSH,
            null,
            null
        )
    }
}