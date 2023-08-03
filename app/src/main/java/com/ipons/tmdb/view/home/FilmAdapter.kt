package com.ipons.tmdb.view.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ipons.domain.model.BasicItemBO
import com.ipons.tmdb.R
import com.ipons.tmdb.extensions.dataBindingInflate

class FilmAdapter(
    private val items: List<BasicItemBO>,
    private val onClick: (BasicItemBO) -> Unit
) : RecyclerView.Adapter<ItemFilmlBasicVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemFilmlBasicVH =
        ItemFilmlBasicVH(parent.dataBindingInflate(R.layout.item_film), onClick)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemFilmlBasicVH, position: Int) {
        holder.bind(items[position])
    }
}