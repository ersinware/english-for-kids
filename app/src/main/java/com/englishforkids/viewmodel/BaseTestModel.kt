package com.englishforkids.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.englishforkids.model.BaseRepository
import kotlin.random.Random

abstract class BaseTestModel<T> : ViewModel() {

    protected abstract val repo: BaseRepository

    abstract val data: Array<T>

    val firstElement = MutableLiveData<T>()

    val secondElement = MutableLiveData<T>()

    val thirdElement = MutableLiveData<T>()

    val fourthElement = MutableLiveData<T>()

    var selected: T? = null

    fun loadElements() {
        val oldElements = getInstantElements()
        firstElement.generateElement(oldElements)
        secondElement.generateElement(oldElements)
        thirdElement.generateElement(oldElements)
        fourthElement.generateElement(oldElements)
        selected = getSelectedRandomly()
    }

    private fun getInstantElements() =
        listOf(
            firstElement.value,
            secondElement.value,
            thirdElement.value,
            fourthElement.value
        )

    private fun MutableLiveData<T>.generateElement(
        oldElements: List<T?>
    ) {
        val secondOldElements = getInstantElements()

        while (true) {
            val newElement = getOneRandomly()
            if (isThereAnySame(newElement, oldElements))
                continue

            if (isThereAnySame(newElement, secondOldElements))
                continue

            value = newElement
            break
        }
    }

    private fun getOneRandomly() =
        data[Random.nextInt(data.size - 1)]

    private fun isThereAnySame(
        newElement: T,
        oldElements: List<T?>
    ): Boolean {
        for (oldElement in oldElements)
            if (newElement == oldElement)
                return true

        return false
    }

    private fun getSelectedRandomly() =
        getInstantElements()[Random.nextInt(4)]!!

    abstract fun speak()
}