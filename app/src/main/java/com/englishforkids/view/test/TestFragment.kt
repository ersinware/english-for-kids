package com.englishforkids.view.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.englishforkids.R
import com.englishforkids.mediautils.SoundPlayer
import com.englishforkids.model.ConfettiRepository
import com.englishforkids.view.utils.*
import com.englishforkids.view.utils.animation.CardClickAnimator
import com.englishforkids.view.utils.animation.awaitFade
import com.englishforkids.view.utils.animation.fadeOut
import com.englishforkids.view.utils.animation.getTransition
import com.englishforkids.viewmodel.BaseTestModel
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.android.synthetic.main.fragment_alphabet_test.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import nl.dionsegijn.konfetti.KonfettiView
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size

abstract class TestFragment<T> : Fragment() {

    protected abstract val binding: ViewDataBinding

    protected abstract val model: BaseTestModel<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupTransitions()
        initBinding()
        initModel()
        setupBinding()
        lifecycleScope.launchWhenStarted {
            setupToolbar()

            if (savedInstanceState != null)
                return@launchWhenStarted

            delay(750)
            model.speak()
        }
    }

    private fun setupTransitions() {
        enterTransition = getTransition(true)
        returnTransition = getTransition(false)
    }

    protected abstract fun initBinding()

    protected abstract fun initModel()

    protected abstract fun setupBinding()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    protected open fun setupToolbar() =
        requireView()
            .findViewById<MaterialToolbar>(R.id.toolbar)
            .setNavigationOnClickListener {
                findNavController().popBackStack()
            }


    private val cardClickAnimator = CardClickAnimator()

    fun onCardClick(card: View, value: T) {
        if (cardClickAnimator.running)
            return

        card.isClickable = false
        lifecycleScope.launch {
            cardClickAnimator.awaitEnd(card)
            if (value == model.selected)
                onCorrect(card as ViewGroup)
            else onIncorrect(card as ViewGroup)
        }
    }

    private fun onCorrect(card: ViewGroup) {
        beforeReset()
        SoundPlayer.getInstance().startSound(true)
        card.addCorrectImage(
            layoutInflater,
            card.background
        )
        popConfetti()

        lifecycleScope.launch {
            delay(2000)
            resetCards()
        }
    }

    protected fun beforeReset() {
        requireView()
            .findViewById<ViewGroup>(R.id.testCardsLayout)
            .setClickableChildren(false)
    }

    private fun popConfetti() =
        requireView()
            .findViewById<KonfettiView>(R.id.confettiView)
            .build()
            .run {
                addColors(ConfettiRepository.colors)
                setDirection(0.0, 360.0)
                setSpeed(1f, 5f)
                setFadeOutEnabled(true)
                setTimeToLive(2000L)
                addShapes(Shape.RECT)
                addSizes(Size(12))
                setPosition(
                    0f,
                    confettiView.width.toFloat(),
                    0f,
                    null
                )
                streamFor(1000, 1000L)
            }

    protected suspend fun resetCards() {
        awaitFadeOut()
        onFadeOut()
        awaitFadeIn()
        onFadeIn()
    }

    private suspend fun awaitFadeOut() {
        requireView()
            .findViewById<View>(R.id.testCardsLayout)
            .awaitFade(false)
    }

    protected open fun onFadeOut() {
        requireView()
            .findViewById<ViewGroup>(R.id.testCardsLayout).run {
                removeLastChildrenOfChildren()
                setVisibilityChildren(View.VISIBLE)
            }
        model.loadElements()
    }

    private suspend fun awaitFadeIn() {
        requireView()
            .findViewById<View>(R.id.testCardsLayout)
            .awaitFade(true)
    }

    private suspend fun onFadeIn() {
        requireView()
            .findViewById<ViewGroup>(R.id.testCardsLayout)
            .setClickableChildren(true)
        delay(750)
        model.speak()
    }

    private fun onIncorrect(card: ViewGroup) {
        SoundPlayer.getInstance().startSound(false)
        card.run {
            addIncorrectImage(
                layoutInflater,
                card.background
            )
            fadeOut()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        for (child in testCardsLayout.children)
            outState.putInt(
                "${child.id}",
                child.visibility
            )
    }

    override fun onViewStateRestored(
        savedInstanceState: Bundle?
    ) {
        super.onViewStateRestored(savedInstanceState)

        savedInstanceState?.let {
            for (child in testCardsLayout.children)
                child.visibility = it.getInt("${child.id}")
        }
    }
}