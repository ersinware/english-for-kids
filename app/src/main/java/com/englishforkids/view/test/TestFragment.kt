package com.englishforkids.view.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.englishforkids.view.utils.animation.CardClickAnimator
import com.englishforkids.view.utils.animation.getTransition
import kotlinx.coroutines.launch

abstract class TestFragment<T> : Fragment() {

    protected abstract val binding: ViewDataBinding

    protected abstract val model: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupTransitions()
        initBinding()
        lifecycleScope.launch {
            setupBinding()
            setupToolbar()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    private fun setupTransitions() {
        enterTransition = getTransition(true)
        returnTransition = getTransition(false)
    }

    protected abstract fun initBinding()

    protected abstract fun setupBinding()

    protected abstract fun setupToolbar()

    fun onCardClick(card: View, value: T) {
        if (CardClickAnimator.running)
            return

        lifecycleScope.launch {
            CardClickAnimator.awaitEnd(card)
            // model.selected == T ????
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        CardClickAnimator.running = false
    }
}