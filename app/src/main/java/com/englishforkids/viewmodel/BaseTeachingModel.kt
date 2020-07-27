package com.englishforkids.viewmodel

interface BaseTeachingModel<T> {

    val data: Array<T>

    fun speak(position: Int)
}