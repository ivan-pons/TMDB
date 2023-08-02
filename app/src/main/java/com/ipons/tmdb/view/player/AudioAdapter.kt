package com.ipons.tmdb.view.player

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.Tracks
import com.ipons.tmdb.R
import com.ipons.tmdb.extensions.dataBindingInflate

class AudioAdapter(
    private val tracks: List<Tracks.Group>,
    private var selectedPosition: Int,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<AudioVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioVH =
        AudioVH(parent.dataBindingInflate(R.layout.item_audio))

    override fun onBindViewHolder(holder: AudioVH, position: Int) {
        if (position != RecyclerView.NO_POSITION) {

            val selected = position == selectedPosition
            holder.bind(selected, tracks[position]) {
                val oldSelected = selectedPosition
                selectedPosition = position
                onClick(position)
                notifyItemChanged(position)
                notifyItemChanged(oldSelected)
            }
        }
    }

    override fun getItemCount(): Int = if (tracks.size <= 7) tracks.size else 7
}