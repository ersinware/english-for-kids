package com.englishforkids.view.test

import android.view.MenuItem
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.englishforkids.databinding.FragmentAlphabetTestBinding
import com.englishforkids.view.utils.lettermode.LetterModeHelper
import com.englishforkids.viewmodel.alphabet.AlphabetTestModel
import com.englishforkids.viewmodel.alphabet.AlphabetTestModelFactory

class AlphabetTestFragment : TestFragment<String>() {

    override lateinit var binding: FragmentAlphabetTestBinding

    override val model by viewModels<AlphabetTestModel> {
        AlphabetTestModelFactory(LetterModeHelper.getLetterMode())
    }

    override fun initBinding() {
        binding = FragmentAlphabetTestBinding
            .inflate(layoutInflater)
            .apply {
                lifecycleOwner = this@AlphabetTestFragment
            }
    }

    override fun setupBinding() =
        binding.run {
            handler = this@AlphabetTestFragment
            model = this@AlphabetTestFragment.model
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

    private fun handleMenuClick(it: MenuItem) {
        it.isChecked = true
        LetterModeHelper.changeLetterMode(
            it.itemId,
            model
        )
    }
}