package com.ipons.tmdb.view.search.sugestions

import androidx.recyclerview.widget.RecyclerView
import com.ipons.tmdb.databinding.ItemSuggestionBinding

class SuggestionItemVH(
    private val binding: ItemSuggestionBinding,
    private val clickEvent: (text: String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(text: String) {
        binding.title.text = text
        binding.root.setOnClickListener {
            clickEvent(text)
        }
    }
}