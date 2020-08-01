package com.englishforkids.view.test

import android.view.MenuItem
import android.view.View
import androidx.core.view.get
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.englishforkids.databinding.FragmentAlphabetTestBinding
import com.englishforkids.view.utils.LetterModeDispatcher
import com.englishforkids.view.utils.removeLastChildrenOfChildren
import com.englishforkids.view.utils.setVisibilityChildren
import com.englishforkids.viewmodel.alphabet.AlphabetTestModel
import com.englishforkids.viewmodel.alphabet.LetterModeHelper
import kotlinx.android.synthetic.main.fragment_alphabet_test.*
import kotlinx.coroutines.launch

class AlphabetTestFragment : TestFragment<String>(), LetterModeDispatcher {

    override lateinit var binding: FragmentAlphabetTestBinding

    override lateinit var model: AlphabetTestModel

    override fun initBinding() {
        binding = FragmentAlphabetTestBinding
            .inflate(layoutInflater)
            .apply {
                lifecycleOwner = this@AlphabetTestFragment
            }
    }

    override fun initModel() {
        model = ViewModelProviders
            .of(this)
            .get(AlphabetTestModel::class.java)
    }

    override fun setupBinding() =
        binding.run {
            handler = this@AlphabetTestFragment
            model = this@AlphabetTestFragment.model
        }

    override fun setupToolbar() {
        super.setupToolbar()
        binding.toolbar.run {
            setOnMenuItemClickListener {
                if (it.isChecked)
                    return@setOnMenuItemClickListener true

                handleMenuClick(it)
                true
            }
            menu[LetterModeHelper.getSavedMode()]
                .isChecked = true
        }

    }

    private var selectedItemId: Int? = null

    private fun handleMenuClick(item: MenuItem) {
        item.isChecked = true
        beforeReset()
        lifecycleScope.launch {
            selectedItemId = item.itemId
            resetCards()
        }
    }

    override fun onFadeOut() {
        selectedItemId?.let {
            testCardsLayout.run {
                removeLastChildrenOfChildren()
                setVisibilityChildren(View.VISIBLE)
            }
            changeLetterMode(selectedItemId!!)
            selectedItemId = null

            return
        }

        super.onFadeOut()
    }
}