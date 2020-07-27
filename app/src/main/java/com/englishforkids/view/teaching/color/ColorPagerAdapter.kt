package com.englishforkids.view.teaching.color

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.englishforkids.databinding.ViewColorBinding
import com.englishforkids.view.utils.animation.PagerClickAnimator
import com.englishforkids.viewmodel.color.ColorModel

class ColorPagerAdapter(
    private val model: ColorModel
) : RecyclerView.Adapter<ColorPagerAdapter.ColorHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ColorHolder(
        ViewColorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = model.data.size

    override fun onBindViewHolder(
        holder: ColorHolder,
        position: Int
    ) = holder.run {
        binding.cardBackgroundColorId = model.data[position]
        itemView.setOnClickListener {
            if (PagerClickAnimator.running)
                return@setOnClickListener

            PagerClickAnimator.start(it)
            model.speak(position)
        }
    }

    inner class ColorHolder(
        internal val binding: ViewColorBinding
    ) : RecyclerView.ViewHolder(
        binding.root
    )
}