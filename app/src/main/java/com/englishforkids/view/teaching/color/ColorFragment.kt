package com.englishforkids.view.teaching.color

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.englishforkids.R
import com.englishforkids.databinding.FragmentColorBinding
import com.englishforkids.view.teaching.TeachingFragment
import com.englishforkids.view.utils.ZoomOutTransformation
import com.englishforkids.viewmodel.color.ColorModel

class ColorFragment : TeachingFragment<Int>() {

    override lateinit var binding: FragmentColorBinding

    override lateinit var model: ColorModel

    override fun initBinding() {
        binding = FragmentColorBinding
            .inflate(layoutInflater)
            .apply {
                lifecycleOwner = this@ColorFragment
            }
    }

    override fun initModel() {
        model = ViewModelProviders
            .of(this)
            .get(ColorModel::class.java)
    }

    override fun setupBinding() =
        binding.run {
            pagerAdapter = ColorPagerAdapter(model, ::onPagerClick)
            pageTransformer = ZoomOutTransformation()
            pagerListener = this@ColorFragment.getPagerListener()
            handler = this@ColorFragment
        }

    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        binding.name = model.colorNames[position]
    }

    override fun setupToolbar() {
        super.setupToolbar()
        binding.toolbar.menu.add(
            getString(
                R.string.colorsTitle
            ) + " " + getString(
                R.string.itsTest
            )
        )
    }

    override fun handleMenuClick(item: MenuItem) {
        findNavController().navigate(
            ColorFragmentDirections
                .actionColorFragmentToColorTestFragment()
        )
    }
}