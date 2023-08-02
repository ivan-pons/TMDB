package com.ipons.tmdb.view.home

import androidx.recyclerview.widget.RecyclerView
import com.ipons.domain.model.ContainerBO
import com.ipons.tmdb.databinding.ItemHomeBasicBinding

class ItemCarouselBasicVH(
    private val binding: ItemHomeBasicBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(container: ContainerBO) {
        binding.carouselTitle.text = container.title
    }

}