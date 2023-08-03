package com.ipons.tmdb.view.home

import androidx.leanback.widget.HorizontalGridView
import androidx.recyclerview.widget.RecyclerView
import com.ipons.domain.model.BasicItemBO
import com.ipons.domain.model.ContainerBO
import com.ipons.tmdb.databinding.ItemHomeBasicBinding

class ItemCarouselBasicVH(
    private val binding: ItemHomeBasicBinding,
    private val onClick: (BasicItemBO) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(container: ContainerBO) {
        binding.carouselTitle.text = container.title
        binding.rvFilms.adapter = FilmAdapter(container.items, onClick)

        binding.rvFilms.windowAlignment = HorizontalGridView.WINDOW_ALIGN_HIGH_EDGE
        binding.rvFilms.windowAlignmentOffsetPercent = 13f
    }

}