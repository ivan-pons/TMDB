package com.ipons.tmdb.view.player

import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.Tracks
import com.ipons.tmdb.databinding.ItemAudioBinding
import com.ipons.tmdb.extensions.capitalized

class AudioVH(private val binding: ItemAudioBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(selected: Boolean, audio: Tracks.Group, onClick: () -> Unit) {
        val label = audio.mediaTrackGroup.getFormat(0).label
            ?: audio.mediaTrackGroup.getFormat(0).language.toString()
        binding.label = label.capitalized()
        binding.selected = selected
        binding.parent.setOnClickListener {
            onClick()
        }
        if (selected) binding.root.requestFocus()
    }
}