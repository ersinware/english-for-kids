package com.englishforkids.view.test

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.englishforkids.R
import com.englishforkids.databinding.FragmentImageTestBinding
import com.englishforkids.view.teaching.image.ImageBindingHelper
import com.englishforkids.viewmodel.image.ImageTestModel
import com.englishforkids.viewmodel.image.ImageTestModelFactory

class ImageTestFragment : TestFragment<Int>() {

    override lateinit var binding: FragmentImageTestBinding

    private val args by navArgs<ImageTestFragmentArgs>()

    override val model by viewModels<ImageTestModel> {
        ImageTestModelFactory(args.target)
    }

    override fun initBinding() {
        binding = FragmentImageTestBinding
            .inflate(layoutInflater)
            .apply {
                lifecycleOwner = this@ImageTestFragment
            }
    }

    override fun setupBinding() =
        binding.run {
            backgroundColorId =
                ImageBindingHelper.getBackgroundColorId(
                    args.target
                )
            cardBackgroundColorId =
                ImageBindingHelper.getCardBackgroundColorId(
                    args.target
                )
            title = ImageBindingHelper.getTitle(
                args.target
            ) + " " + getString(R.string.itsTest)
            handler = this@ImageTestFragment
            model = this@ImageTestFragment.model
        }

    override fun setupToolbar() =
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
}