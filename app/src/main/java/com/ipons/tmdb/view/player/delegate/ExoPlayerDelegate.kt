package com.ipons.tmdb.view.player.delegate

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.MediaItem.DrmConfiguration
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.analytics.AnalyticsListener
import com.google.android.exoplayer2.trackselection.TrackSelectionOverride
import com.google.android.exoplayer2.ui.PlayerView
import com.ipons.domain.model.SourceBO
import com.ipons.tmdb.R
import com.ipons.tmdb.view.player.AudioAdapter
import com.ipons.tmdb.view.player.FORWARD_REWIND_EXOPLAYER
import com.ipons.tmdb.view.player.youbora.YouboraManager


class ExoPlayerDelegate(
    private val context: Context,
    private val youboraManager: YouboraManager
) : PlayerDelegate {

    private lateinit var playerView: PlayerView
    private lateinit var player: ExoPlayer
    private var handler: Handler = Handler(Looper.getMainLooper())
    private val runnableCode by lazy { Runnable { updatePositionTimeInSeconds() } }
    private var event: (CustomEventType) -> Unit = {}


    fun createViewPlayer(): View {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.exoplayer_layout, null, false)
        playerView = view.rootView as PlayerView
        return view
    }

    override fun initPlayer(error: () -> Unit) {
        //¿ponerlo en un try-catch para usar el error callback?
        player = ExoPlayer.Builder(context).build()
        playerView.player = player
        youboraManager.setAdapter(player)
    }

    override fun playMedia(streamInfo: SourceBO, current: Int) {
        val drmConfig = getDrmConfig(streamInfo)
        val mediaItem: MediaItem = MediaItem.Builder()
            .setUri(streamInfo.manifestUrl)
            .setDrmConfiguration(drmConfig?.build())
            .build()

        player.setMediaItem(mediaItem)
        player.prepare()
        player.seekTo(current * 1000L)
        player.playWhenReady = true
    }

    private fun getDrmConfig(streamInfo: SourceBO) =
        if (streamInfo.drmLicense.isNotEmpty()) {
            DrmConfiguration.Builder(C.WIDEVINE_UUID)
                .setLicenseUri(streamInfo.drmLicense)
        } else null

    override fun onEventListener(event: (CustomEventType) -> Unit) {
        this.event = event
        player.addAnalyticsListener(
            object : AnalyticsListener {
                override fun onRenderedFirstFrame(
                    eventTime: AnalyticsListener.EventTime,
                    output: Any,
                    renderTimeMs: Long
                ) {
                    super.onRenderedFirstFrame(eventTime, output, renderTimeMs)
                    player.removeAnalyticsListener(this)
                    event(CustomEventType.StartPlay(getDuration().toInt()))
                }
            }
        )

        player.addListener(object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                event(CustomEventType.Error)
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                super.onIsPlayingChanged(isPlaying)
                if (isPlaying) {
                    event(CustomEventType.Play)
                    initUpdatePositionListener()
                } else {
                    event(CustomEventType.Pause)
                    stopUpdatePositionListener()
                }
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                super.onPlaybackStateChanged(playbackState)

                if (playbackState == Player.STATE_ENDED) {
                    event(CustomEventType.Finish)
                }
            }
        })
    }

    override fun manageAudioTracks(): AudioAdapter? {
        val audioTracks = player.currentTracks.groups.filter { it.type == C.TRACK_TYPE_AUDIO }
        val adapter: AudioAdapter?

        if (audioTracks.size > 1) {
            val trackSelected = audioTracks.firstOrNull { it.isSelected }
            val selected = audioTracks.indexOf(trackSelected)

            adapter = AudioAdapter(audioTracks, selected) { position ->
                val newSelected = audioTracks[position]
                player.trackSelectionParameters = player.trackSelectionParameters
                    .buildUpon()
                    .setOverrideForType(
                        TrackSelectionOverride(
                            newSelected.mediaTrackGroup,
                            0
                        )
                    )
                    .build()
            }
            return adapter
        } else {
            return null
        }
    }

    override fun onResume() {
        if (this::player.isInitialized) {
            player.play()
        }
    }

    override fun onPause() {
        if (this::player.isInitialized) {
            player.pause()
        }
    }

    override fun onDestroy() {
        releasePlayer()
    }

    override fun playPause() {
        if (this::player.isInitialized) {
            if (player.isPlaying) {
                player.pause()
            } else {
                player.play()
            }
        }
    }

    override fun forward() {
        if ((player.currentPosition + FORWARD_REWIND_EXOPLAYER.toLong()) < player.duration) {
            player.seekTo(player.currentPosition + FORWARD_REWIND_EXOPLAYER.toLong())
        } else {
            player.seekTo(player.duration)
        }
    }

    override fun rewind() {
        if ((player.currentPosition - FORWARD_REWIND_EXOPLAYER.toLong()) > 0) {
            player.seekTo(player.currentPosition - FORWARD_REWIND_EXOPLAYER.toLong())
        } else {
            player.seekTo(0)
        }
    }

    override fun seek(position: Int) {
        player.seekTo(position.toLong() * 1000)
    }

    override fun stopPlayer() {
        if (::player.isInitialized) player.stop()
    }

    override fun getCurrentTime() = player.currentPosition.toInt() / 1000

    override fun getDuration() = if (this::player.isInitialized) {
        player.duration / 1000
    } else {
        0
    }

    private fun updatePositionTimeInSeconds() {
        if (player.isPlaying) {
            event(CustomEventType.TimeChanged(getCurrentTime()))
        }
        handler.removeCallbacks(runnableCode)
        handler.postDelayed(runnableCode, 1000)
    }

    private fun initUpdatePositionListener() {
        handler.removeCallbacks(runnableCode)
        handler.postDelayed(runnableCode, 1000)
    }

    private fun stopUpdatePositionListener() {
        handler.removeCallbacks(runnableCode)
    }

    private fun releasePlayer() {
        if (this::player.isInitialized) {
            player.release()
        }
    }
}