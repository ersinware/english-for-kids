package com.englishforkids.viewmodel

interface SpeakerModel<T> {

    val data: Array<T>

    fun findIndex(selected: T): Int {
        for ((index, id) in data.withIndex())
            if (id == selected)
                return index

        throw Throwable("selected index cannot be found")
    }
}