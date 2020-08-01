package com.englishforkids.view.teaching.alphabet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.englishforkids.databinding.ViewLetterBinding
import com.englishforkids.viewmodel.alphabet.AlphabetModel

class AlphabetPagerAdapter(
    private val model: AlphabetModel,
    private val onClick: (View, Int) -> Unit
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
            onClick.invoke(it, position)
        }
    }

    inner class LetterHolder(
        val binding: ViewLetterBinding
    ) : RecyclerView.ViewHolder(
        binding.root
    )
}