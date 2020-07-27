package com.englishforkids.view.teaching.image

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.englishforkids.databinding.ViewImageBinding
import com.englishforkids.view.appContext
import com.englishforkids.view.utils.animation.PagerClickAnimator
import com.englishforkids.viewmodel.image.ImageModel

class ImagePagerAdapter(
    private val model: ImageModel
) : RecyclerView.Adapter<ImagePagerAdapter.ImageHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ImageHolder(
        ViewImageBinding.inflate(
            LayoutInflater.from(appContext),
            parent,
            false
        )
    )

    override fun getItemCount() = model.data.size

    override fun onBindViewHolder(
        holder: ImageHolder,
        position: Int
    ) = holder.run {
        binding.imageId = model.data[position]
        itemView.setOnClickListener {
            if (PagerClickAnimator.running)
                return@setOnClickListener

            PagerClickAnimator.start(it)
            model.speak(position)
        }
    }

    inner class ImageHolder(
        internal val binding: ViewImageBinding
    ) : RecyclerView.ViewHolder(
        binding.root
    )
}