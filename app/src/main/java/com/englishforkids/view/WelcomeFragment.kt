package com.englishforkids.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.englishforkids.R
import com.englishforkids.databinding.FragmentWelcomeBinding
import com.englishforkids.view.utils.animation.getFirstTransition
import com.englishforkids.view.utils.animation.getTransition
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    private lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupTransitions()
        initBinding()
        job = lifecycleScope.launch {
            delay(2000)
            goHomepage()
        }
    }

    private fun setupTransitions() {
        exitTransition = getFirstTransition(true)
    }

    private fun initBinding() {
        binding = FragmentWelcomeBinding
            .inflate(layoutInflater)
            .apply {
                imageUrl = "welcome.png"
                lifecycleOwner = this@WelcomeFragment
            }
    }

    private fun goHomepage() =
        findNavController().navigate(
            WelcomeFragmentDirections
                .actionWelcomeFragmentToHomepageFragment()
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onStart() {
        super.onStart()

        if (job.isCancelled)
            job = lifecycleScope.launch {
                delay(1000)
                goHomepage()
            }
    }

    override fun onStop() {
        super.onStop()

        job.run {
            if (!isCompleted) cancel()
        }
    }
}