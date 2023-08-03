package com.ipons.tmdb.view.home

import androidx.recyclerview.widget.RecyclerView
import com.ipons.domain.model.BasicItemBO
import com.ipons.tmdb.databinding.ItemFilmBinding

class ItemFilmlBasicVH(
    private val binding: ItemFilmBinding,
    private val onClick: (BasicItemBO) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(film: BasicItemBO) {
        binding.url = film.posterImage
        binding.itemContainer.setOnClickListener {
            onClick(film)
        }
    }

}