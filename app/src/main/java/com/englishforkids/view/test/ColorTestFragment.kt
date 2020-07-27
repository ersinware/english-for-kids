package com.englishforkids.view.test

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.englishforkids.databinding.FragmentColorTestBinding
import com.englishforkids.viewmodel.color.ColorTestModel

class ColorTestFragment : TestFragment<Int>() {

    override lateinit var binding: FragmentColorTestBinding

    override val model by viewModels<ColorTestModel>()

    override fun initBinding() {
        binding = FragmentColorTestBinding
            .inflate(layoutInflater)
            .apply {
                lifecycleOwner = this@ColorTestFragment
            }
    }

    override fun setupBinding() {
        binding.apply {
            handler = this@ColorTestFragment
            model = this@ColorTestFragment.model
        }
    }

    override fun setupToolbar() =
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
}