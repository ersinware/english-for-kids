package com.englishforkids.view.teaching

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.englishforkids.view.utils.animation.ButtonClickAnimator
import com.englishforkids.view.utils.animation.PagerClickAnimator
import com.englishforkids.view.utils.animation.getTransition
import kotlinx.coroutines.launch

abstract class TeachingFragment : Fragment() {

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

    private fun setupTransitions() {
        enterTransition = getTransition(true)
        exitTransition = getTransition(true)
        returnTransition = getTransition(false)
        reenterTransition = getTransition(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    protected abstract fun initBinding()

    protected abstract fun setupBinding()

    protected abstract fun manageButtons(position: Int)

    protected fun manageButton(btn: View, condition: Boolean) {
        if (btn.isEnabled == !condition)
            return

        btn.isEnabled = !condition
        btn.animate().alpha(
            if (condition)
                0.5f
            else
                1f
        )
    }

    protected abstract fun setupToolbar()

    protected abstract fun handleMenuClick(item: MenuItem)

    open fun onPreviousClick(v: View) {
        ButtonClickAnimator.start(v)
    }

    open fun onNextClick(v: View) {
        ButtonClickAnimator.start(v)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        PagerClickAnimator.running = false
    }
}