package com.englishforkids.view.teaching.image

import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.englishforkids.R
import com.englishforkids.databinding.FragmentImageBinding
import com.englishforkids.view.teaching.TeachingFragment
import com.englishforkids.view.utils.ZoomOutTransformation
import com.englishforkids.viewmodel.image.ImageModel
import com.englishforkids.viewmodel.image.ImageModelFactory
import kotlinx.android.synthetic.main.fragment_image.*

class ImageFragment : TeachingFragment() {

    override lateinit var binding: FragmentImageBinding

    private val args by navArgs<ImageFragmentArgs>()

    override val model by viewModels<ImageModel> {
        ImageModelFactory(args.target)
    }

    override fun initBinding() {
        binding = FragmentImageBinding
            .inflate(layoutInflater)
            .apply {
                lifecycleOwner = this@ImageFragment
            }
    }

    override fun setupBinding() =
        binding.run {
            backgroundColorId = ImageBindingHelper.getBackgroundColorId(args.target)
            title = ImageBindingHelper.getTitle(args.target)
            pagerAdapter = ImagePagerAdapter(model)
            pageTransformer = ZoomOutTransformation()
            pagerListener = object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    manageButtons(position)
                    model.speak(position)
                    binding.name = model.imageNames[position]
                }
            }
            handler = this@ImageFragment
            buttonColorId = ImageBindingHelper.getButtonColorId(args.target)
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
                binding.title + " " + getString(
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
            ImageFragmentDirections
                .actionImageFragmentToImageTestFragment(args.target)
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
}