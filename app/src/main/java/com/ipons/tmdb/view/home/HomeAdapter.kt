package com.ipons.tmdb.view.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ipons.domain.model.BasicItemBO
import com.ipons.domain.model.ContainerBO
import com.ipons.tmdb.R
import com.ipons.tmdb.extensions.dataBindingInflate

class HomeAdapter(
    private val home: List<ContainerBO>,
    private val onClick: (BasicItemBO) -> Unit
) : RecyclerView.Adapter<ItemCarouselBasicVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCarouselBasicVH =
        ItemCarouselBasicVH(parent.dataBindingInflate(R.layout.item_home_basic), onClick)

    override fun getItemCount(): Int = home.size

    override fun onBindViewHolder(holder: ItemCarouselBasicVH, position: Int) {
        holder.bind(home[position])
    }
}