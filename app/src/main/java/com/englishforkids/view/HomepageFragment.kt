package com.englishforkids.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.englishforkids.R
import com.englishforkids.databinding.FragmentHomepageBinding
import com.englishforkids.view.utils.Target
import com.englishforkids.view.utils.animation.CardClickAnimator
import com.englishforkids.view.utils.animation.getFirstTransition
import com.englishforkids.view.utils.animation.getTransition
import kotlinx.android.synthetic.main.fragment_homepage.*
import kotlinx.coroutines.launch

class HomepageFragment : Fragment() {

    private lateinit var binding: FragmentHomepageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupTransitions()
        initBinding()
        setupBinding()
        setupToolbar()
    }

    private fun setupTransitions() {
        enterTransition = getFirstTransition(true)
        exitTransition = getTransition(true)
        reenterTransition = getTransition(false)
    }

    private fun initBinding() {
        binding = FragmentHomepageBinding
            .inflate(layoutInflater)
            .apply {
                lifecycleOwner = this@HomepageFragment
            }
    }

    private fun setupBinding() =
        binding.run {
            handler = this@HomepageFragment
            colorsImageUrl = "homepageImages/colors.png"
            alphabetImageUrl = "homepageImages/alphabet.png"
            animalsImageUrl = "animals/Elephant.png"
            fruitsImageUrl = "fruits/Watermelon.png"
            objectsImageUrl = "objects/Airplane.png"
            numbersImageUrl = "numbers/3Three.png"
        }

    private fun setupToolbar() =
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.itemShare -> onShareClick()
                R.id.itemRate -> onRateClick()
                else -> onOtherAppsClick()
            }
            true
        }

    private fun onShareClick() {
        startActivity(
            Intent.createChooser(
                Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "https://play.google.com/store/apps/" +
                                "details?id=english.for.kids"
                    )
                },
                resources.getString(R.string.share)
            )
        )
    }

    private fun onRateClick() {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(
                    "https://play.google.com/store/apps/" +
                            "details?id=english.for.kids"
                )
            )
        )
    }

    private fun onOtherAppsClick() {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(
                    "https://play.google.com/store/apps/" +
                            "dev?id=7294882346252393987"
                )
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    private val cardClickAnimator = CardClickAnimator()

    fun onCardClick(card: View) {
        if (cardClickAnimator.running)
            return

        lifecycleScope.launch {
            cardClickAnimator.awaitEnd(card)
            handleClick(card)
        }
    }

    private fun handleClick(card: View) =
        findNavController().navigate(
            when (val target = getTarget(card)) {
                Target.TARGET_COLOR ->
                    HomepageFragmentDirections
                        .actionHomepageFragmentToColorFragment()
                Target.TARGET_ALPHABET ->
                    HomepageFragmentDirections
                        .actionHomepageFragmentToAlphabetFragment()
                else ->
                    HomepageFragmentDirections
                        .actionHomepageFragmentToImageFragment(target)
            }
        )

    private fun getTarget(card: View) =
        when (card) {
            colorsCard -> Target.TARGET_COLOR
            alphabetCard -> Target.TARGET_ALPHABET
            animalsCard -> Target.TARGET_ANIMALS
            fruitsCard -> Target.TARGET_FRUITS
            objectsCard -> Target.TARGET_OBJECTS
            else -> Target.TARGET_NUMBERS
        }
}