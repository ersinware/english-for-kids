package com.englishforkids.view.teaching.color

import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.englishforkids.R
import com.englishforkids.databinding.FragmentColorBinding
import com.englishforkids.view.teaching.TeachingFragment
import com.englishforkids.view.utils.ZoomOutTransformation
import com.englishforkids.view.utils.animation.PagerClickAnimator
import com.englishforkids.viewmodel.color.ColorModel
import kotlinx.android.synthetic.main.fragment_color.*

class ColorFragment : TeachingFragment() {

    override lateinit var binding: FragmentColorBinding

    override val model by viewModels<ColorModel>()

    override fun initBinding() {
        binding = FragmentColorBinding
            .inflate(layoutInflater)
            .apply {
                lifecycleOwner = this@ColorFragment
            }
    }

    override fun setupBinding() =
        binding.run {
            pagerAdapter = ColorPagerAdapter(model)
            pageTransformer = ZoomOutTransformation()
            pagerListener = object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    manageButtons(position)
                    model.speak(position)
                    binding.name = model.colorNames[position]
                }
            }
            handler = this@ColorFragment
        }

    override fun manageButtons(position: Int) {
        manageButton(
            btnNext,
            position == model.data.size - 1
        )
        manageButton(
            btnPrevious,
            position == 0
        )
    }

    override fun setupToolbar() =
        binding.toolbar.run {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            menu.add(
                getString(
                    R.string.colorsTitle
                ) + " " + getString(
                    R.string.itsTest
                )
            )

            setOnMenuItemClickListener {
                handleMenuClick(it)
                true
            }
        }

    override fun handleMenuClick(item: MenuItem) {
        findNavController().navigate(
            ColorFragmentDirections
                .actionColorFragmentToColorTestFragment()
        )
    }

    override fun onPreviousClick(v: View) {
        super.onPreviousClick(v)
        pager.currentItem -= 1
    }

    override fun onNextClick(v: View) {
        super.onNextClick(v)
        pager.currentItem += 1
    }

    override fun onDestroyView() {
        super.onDestroyView()
        PagerClickAnimator.running = false
    }
}