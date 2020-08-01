package com.englishforkids.view.test

import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.englishforkids.R
import com.englishforkids.databinding.FragmentImageTestBinding
import com.englishforkids.view.teaching.image.ImageBindingHelper
import com.englishforkids.viewmodel.image.ImageTestModel
import com.englishforkids.viewmodel.image.ImageTestModelFactory

class ImageTestFragment : TestFragment<String>() {

    override lateinit var binding: FragmentImageTestBinding

    private val args by navArgs<ImageTestFragmentArgs>()

    override lateinit var model: ImageTestModel

    override fun initBinding() {
        binding = FragmentImageTestBinding
            .inflate(layoutInflater)
            .apply {
                lifecycleOwner = this@ImageTestFragment
            }
    }

    override fun initModel() {
        model = ViewModelProviders
            .of(
                this,
                ImageTestModelFactory(args.target)
            )
            .get(ImageTestModel::class.java)
    }

    override fun setupBinding() =
        binding.run {
            backgroundColorId =
                ImageBindingHelper.getBackgroundColorId(args.target)
            cardBackgroundColorId =
                ImageBindingHelper.getCardBackgroundColorId(args.target)
            title = ImageBindingHelper.getTitle(args.target) + " " + getString(R.string.itsTest)
            handler = this@ImageTestFragment
            model = this@ImageTestFragment.model
        }
}