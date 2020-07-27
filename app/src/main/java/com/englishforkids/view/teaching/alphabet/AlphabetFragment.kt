package com.englishforkids.view.teaching.alphabet

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.englishforkids.databinding.FragmentAlphabetBinding
import com.englishforkids.view.teaching.TeachingFragment
import com.englishforkids.view.utils.lettermode.LetterModeHelper
import com.englishforkids.view.utils.ZoomOutTransformation
import com.englishforkids.viewmodel.alphabet.AlphabetModel
import com.englishforkids.viewmodel.alphabet.AlphabetModelFactory
import kotlinx.android.synthetic.main.fragment_alphabet.*

class AlphabetFragment : TeachingFragment() {

    override lateinit var binding: FragmentAlphabetBinding

    override val model by viewModels<AlphabetModel> {
        AlphabetModelFactory(LetterModeHelper.getLetterMode())
    }

    private var viewDestroyed = false

    override fun initBinding() {
        binding = FragmentAlphabetBinding
            .inflate(layoutInflater)
            .apply {
                lifecycleOwner = this@AlphabetFragment
            }
    }

    override fun setupBinding() =
        binding.run {
            pagerAdapter = AlphabetPagerAdapter(model)
            pageTransformer = ZoomOutTransformation()
            pagerListener = object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    manageButtons(position)
                    model.speak(position)
                }
            }
            handler = this@AlphabetFragment
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

            setCheckedItem()

            setOnMenuItemClickListener {
                handleMenuClick(it)
                true
            }
        }

    private fun setCheckedItem() {
        binding
            .toolbar
            .menu[LetterModeHelper.getSavedMode()]
            .isChecked = true
    }

    override fun handleMenuClick(item: MenuItem) {
        if (item.isCheckable) {
            item.isChecked = true
            LetterModeHelper.changeLetterMode(
                item.itemId,
                model
            )
        } else
            findNavController().navigate(
                AlphabetFragmentDirections
                    .actionAlphabetFragmentToAlphabetTestFragment()
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

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        if (!viewDestroyed)
            return

        setCheckedItem()
        viewDestroyed = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewDestroyed = true
    }
}
