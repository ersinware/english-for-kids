package com.englishforkids.view.teaching.image

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.englishforkids.R
import com.englishforkids.databinding.FragmentImageBinding
import com.englishforkids.view.teaching.TeachingFragment
import com.englishforkids.view.utils.ZoomOutTransformation
import com.englishforkids.viewmodel.image.ImageModel
import com.englishforkids.viewmodel.image.ImageModelFactory
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.android.synthetic.main.fragment_image.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ImageFragment : TeachingFragment<String>() {

    override lateinit var binding: FragmentImageBinding

    private val args by navArgs<ImageFragmentArgs>()

    override lateinit var model: ImageModel

    override fun initBinding() {
        binding = FragmentImageBinding
            .inflate(layoutInflater)
            .apply {
                lifecycleOwner = this@ImageFragment
            }
    }

    override fun initModel() {
        model = ViewModelProviders
            .of(
                this,
                ImageModelFactory(args.target)
            )
            .get(ImageModel::class.java)
    }

    override fun setupBinding() =
        binding.run {
            backgroundColorId = ImageBindingHelper.getBackgroundColorId(args.target)
            title = ImageBindingHelper.getTitle(args.target)
            pagerAdapter = ImagePagerAdapter(model, ::onPagerClick)
            pageTransformer = ZoomOutTransformation()
            pagerListener = this@ImageFragment.getPagerListener()
            handler = this@ImageFragment
            buttonColorId = ImageBindingHelper.getButtonColorId(args.target)
        }

    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        binding.name = model.imageNames[position]
    }

    override fun setupToolbar() {
        super.setupToolbar()
        binding.toolbar.menu.add(
            binding.title + " " + getString(
                R.string.itsTest
            )
        )
    }

    override fun handleMenuClick(item: MenuItem) {
        findNavController().navigate(
            ImageFragmentDirections
                .actionImageFragmentToImageTestFragment(args.target)
        )
    }
}