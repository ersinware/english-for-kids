package com.englishforkids.viewmodel

interface BaseTestModel<T> {

    val data: Array<T>

    fun speak()
}