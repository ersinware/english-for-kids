package com.englishforkids.view.test

import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.englishforkids.databinding.FragmentColorTestBinding
import com.englishforkids.view.utils.removeChildrenOfChildren
import com.englishforkids.view.utils.setVisibilityChildren
import com.englishforkids.viewmodel.color.ColorTestModel
import kotlinx.android.synthetic.main.fragment_alphabet_test.*

class ColorTestFragment : TestFragment<Int>() {

    override lateinit var binding: FragmentColorTestBinding

    override lateinit var model: ColorTestModel

    override fun initBinding() {
        binding = FragmentColorTestBinding
            .inflate(layoutInflater)
            .apply {
                lifecycleOwner = this@ColorTestFragment
            }
    }

    override fun initModel() {
        model = ViewModelProviders
            .of(this)
            .get(ColorTestModel::class.java)
    }

    override fun setupBinding() {
        binding.apply {
            handler = this@ColorTestFragment
            model = this@ColorTestFragment.model
        }
    }

    override fun onFadeOut() {
        testCardsLayout.run {
            removeChildrenOfChildren()
            setVisibilityChildren(View.VISIBLE)
        }
        model.loadElements()
    }
}