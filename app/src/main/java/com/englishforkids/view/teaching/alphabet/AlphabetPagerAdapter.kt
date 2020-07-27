package com.englishforkids.view.teaching.alphabet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.englishforkids.databinding.ViewLetterBinding
import com.englishforkids.view.utils.animation.PagerClickAnimator
import com.englishforkids.viewmodel.alphabet.AlphabetModel

class AlphabetPagerAdapter(
    private val model: AlphabetModel
) : RecyclerView.Adapter<AlphabetPagerAdapter.LetterHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = LetterHolder(
        ViewLetterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = model.data.size

    override fun onBindViewHolder(
        holder: LetterHolder,
        position: Int
    ) = holder.run {
        binding.letter = model.data[position]
        itemView.setOnClickListener {
            if (PagerClickAnimator.running)
                return@setOnClickListener

            PagerClickAnimator.start(it)
            model.speak(position)
        }
    }

    inner class LetterHolder(
        val binding: ViewLetterBinding
    ) : RecyclerView.ViewHolder(
        binding.root
    )
}