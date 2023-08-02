package com.ipons.tmdb.view.search

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ipons.domain.model.BasicItemBO
import com.ipons.tmdb.R
import com.ipons.tmdb.extensions.dataBindingInflate

class SearchAdapter(
    private val items: List<BasicItemBO>
) : RecyclerView.Adapter<ItemSearchVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemSearchVH =
        ItemSearchVH(parent.dataBindingInflate(R.layout.item_search))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemSearchVH, position: Int) {
        holder.bind(items[position])
    }
}