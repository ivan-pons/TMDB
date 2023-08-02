package com.ipons.tmdb.view.player.delegate

import com.ipons.domain.model.SourceBO
import com.ipons.tmdb.view.player.AudioAdapter
import com.ipons.tmdb.view.player.CustomEventType


interface PlayerDelegate {
    fun initPlayer(error: () -> Unit)
    fun playMedia(streamInfo: SourceBO, current: Int)
    fun onEventListener(event: (CustomEventType) -> Unit)
    fun manageAudioTracks(): AudioAdapter?
    fun onResume()
    fun onPause()
    fun onDestroy()
    fun playPause()
    fun forward()
    fun rewind()
    fun seek(position: Int)
    fun stopPlayer()
    fun getCurrentTime(): Int
    fun getDuration(): Long
}
