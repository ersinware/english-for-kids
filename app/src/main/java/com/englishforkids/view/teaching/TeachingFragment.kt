package com.englishforkids.view.teaching

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.englishforkids.R
import com.englishforkids.view.utils.animation.ButtonClickAnimator
import com.englishforkids.view.utils.animation.PagerClickAnimator
import com.englishforkids.view.utils.animation.getTransition
import com.englishforkids.viewmodel.BaseTeachingModel
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class TeachingFragment<T> : Fragment() {

    protected abstract val binding: ViewDataBinding

    protected abstract val model: BaseTeachingModel<T>

    private var state: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        state = savedInstanceState
        setupTransitions()
        initBinding()
        initModel()
        setupBinding()
        lifecycleScope.launchWhenStarted {
            setupToolbar()
        }
    }

    private fun setupTransitions() {
        enterTransition = getTransition(true)
        exitTransition = getTransition(true)
        returnTransition = getTransition(false)
        reenterTransition = getTransition(false)
    }

    protected abstract fun initBinding()

    abstract fun initModel()

    protected abstract fun setupBinding()

    protected fun onPagerClick(view: View, position: Int) {
        if (PagerClickAnimator.running)
            return

        lifecycleScope.launch {
            model.speak(position)
            PagerClickAnimator.awaitEnd(view)
        }
    }

    protected fun getPagerListener() =
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                this@TeachingFragment.onPageSelected(position)
            }
        }

    protected open fun onPageSelected(position: Int) {
        manageButtons(position)

        if (state != null) {
            state = null
            return
        }

        lifecycleScope.launch {
            delay(500)
            model.speak(position)
        }
    }

    private fun manageButtons(position: Int) {
        manageButton(
            requireView().findViewById(R.id.btnNext),
            position == model.data.size - 1
        )
        manageButton(
            requireView().findViewById(R.id.btnPrevious),
            position == 0
        )
    }

    private fun manageButton(
        btn: View,
        condition: Boolean
    ) {
        if (btn.isEnabled == !condition)
            return

        btn.isEnabled = !condition
        btn.animate().alpha(
            if (condition) 0.5f
            else 1f
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    protected open fun setupToolbar() =
        requireView()
            .findViewById<MaterialToolbar>(R.id.toolbar)
            .run {
                setNavigationOnClickListener {
                    findNavController().popBackStack()
                }
                setOnMenuItemClickListener {
                    handleMenuClick(it)
                    true
                }
            }

    protected abstract fun handleMenuClick(item: MenuItem)

    fun onPreviousClick(v: View, pager: ViewPager2) {
        ButtonClickAnimator.start(v)
        pager.currentItem -= 1
    }

    fun onNextClick(v: View, pager: ViewPager2) {
        ButtonClickAnimator.start(v)
        pager.currentItem += 1
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("saved", true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        PagerClickAnimator.running = false
    }
}