package com.ipons.tmdb.view.player

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.transition.TransitionManager
import android.view.View
import android.widget.SeekBar
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import com.ipons.domain.model.SourceBO
import com.ipons.tmdb.R
import com.ipons.tmdb.databinding.ActivityPlayerBinding
import com.ipons.tmdb.utils.convertToMinutesAndSecondsFormat
import com.ipons.tmdb.view.player.delegate.CustomEventType
import com.ipons.tmdb.view.player.delegate.ExoPlayerDelegate
import com.ipons.tmdb.view.player.delegate.PlayerDelegate
import com.ipons.tmdb.view.player.youbora.YouboraManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlayerActivity : FragmentActivity() {

    private lateinit var binding: ActivityPlayerBinding
    private val playerViewModel by viewModels<PlayerViewModel>()

    private lateinit var handler: Handler
    private lateinit var hideControlsRunnable: Runnable
    private var handlerSeekBar: Handler = Handler(Looper.getMainLooper())
    private var runnableSeekBar: Runnable = Runnable { }

    private lateinit var playerDelegate: PlayerDelegate

    private var isSeeking = false
    private var isAudioSet = false

    @Inject
    lateinit var youboraManager: YouboraManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        youboraManager.setActivity(this)

        initFocus()
        initHandler()
        initButtons()
        initPlayer()
    }

    private fun initPlayer() {
        prepareMedia(SourceBO(), 0)
    }

    private fun prepareMedia(source: SourceBO, current: Int) {
        val exoPlayerDelegate = ExoPlayerDelegate(this, youboraManager)
        playerDelegate = exoPlayerDelegate
        binding.playerContainer.addView(exoPlayerDelegate.createViewPlayer())
        playerDelegate.initPlayer {
            closePlayer()
//            showError(R.string.nexplayer_init_error)
        }
        initAndPlay(source, current)
    }

    private fun initAndPlay(source: SourceBO, current: Int) {
        initializeListeners()
        playerDelegate.playMedia(source, current)
    }

    private fun initializeListeners() {

        playerDelegate.onEventListener { event ->
            Handler(Looper.getMainLooper()).post {
                when (event) {
                    CustomEventType.Error -> {
                        closePlayer()
//                            showError(R.string.stream_no_content)
                    }

                    CustomEventType.Play -> {
                        setPauseImage()
                        isSeeking = false
                    }

                    CustomEventType.Pause -> {
                        setPlayImage()
                    }

                    is CustomEventType.TimeChanged -> {
                        updateTime(event.currentTime)
                    }

                    is CustomEventType.StartPlay -> {
                        setPauseImage()
                        setDuration(event.duration)
                    }

                    CustomEventType.Seek -> isSeeking = false
                    else -> {}
                }
            }
        }
    }

    private fun setPlayImage() {
        binding.icPlayPause.setImageResource(R.drawable.ic_play)
    }

    private fun setPauseImage() {
        binding.icPlayPause.setImageResource(R.drawable.ic_pause)
    }

    private fun setDuration(duration: Int) {
        binding.seekbar.max = duration
    }

    private fun updateTime(currentTime: Int) {
        if (!isSeeking) {
            binding.tvTimeLeft.text =
                (binding.seekbar.max - currentTime).toDouble()
                    .convertToMinutesAndSecondsFormat()
            binding.seekbar.progress = currentTime
            binding.tvTimeElapsed.text = currentTime.toDouble().convertToMinutesAndSecondsFormat()
        }

    }

    override fun onResume() {
        initBackButton()
        if (this::playerDelegate.isInitialized) {
            playerDelegate.onResume()
        }
        super.onResume()
    }

    override fun onPause() {
        if (this::playerDelegate.isInitialized) {
            closePlayer()
            playerDelegate.onPause()
        }
        super.onPause()
    }

    override fun onDestroy() {
        releasePlayer()
        super.onDestroy()
    }

    private fun closePlayer() {
        youboraManager.removeAdapter()
        finish()
    }

    private fun releasePlayer() {
        if (this::playerDelegate.isInitialized) {
            playerDelegate.onDestroy()
        }
    }

    private fun initBackButton() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBackPresed()
                }
            }
        this.onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun onBackPresed() {
        when {
            binding.overlayAudio.isVisible -> hideAudio()
            binding.overlayPlayer.isVisible -> hideControls(0)
            else -> finish()
        }
    }

    private fun hideAudio() {
        showVodOverlay()
        binding.btnAudio.requestFocus()
        binding.overlayAudio.isVisible = false
    }

    private fun initFocus() {
        binding.focusCatcher.requestFocus()
        binding.focusCatcher.setOnClickListener {
            TransitionManager.beginDelayedTransition(binding.root)
            showVodOverlay()
        }

        binding.overlayPlayer.viewTreeObserver.addOnGlobalFocusChangeListener { _, _ ->
            hideControls(3)
        }
    }

    private fun showVodOverlay() {
        binding.overlayPlayer.isVisible = true
        binding.btnPlay.requestFocus()
        setAudio()
        hideControls(10)
    }

    private fun hideControls(afterSeconds: Int) {
        handler.removeCallbacks(hideControlsRunnable)
        handler.postDelayed(hideControlsRunnable, afterSeconds * 1000.toLong())
    }

    private fun setAudio() {
        if (this::playerDelegate.isInitialized && !isAudioSet) {
            isAudioSet = true
            val adapter = playerDelegate.manageAudioTracks()

            binding.btnAudio.isVisible = adapter != null
            adapter?.let {
                binding.rvAudioTracks.adapter = adapter
            }
        }
    }

    private fun initHandler() {
        handler = Handler(Looper.getMainLooper())
        hideControlsRunnable = Runnable {
            if (binding.overlayPlayer.isVisible) {
                binding.overlayPlayer.visibility = View.GONE
                binding.focusCatcher.requestFocus()
            }
        }
    }

    private fun showAudio() {
        binding.overlayAudio.isVisible = true
        binding.overlayPlayer.visibility = View.GONE
        binding.rvAudioTracks.requestFocus()
    }


    private fun initButtons() {

        binding.btnAudio.setOnClickListener {
            showAudio()
        }
        binding.btnPlay.setOnClickListener {
            playerDelegate.playPause()
            hideControls(3)
        }
        binding.btnForward.setOnClickListener {
            playerDelegate.forward()
            hideControls(3)
        }
        binding.btnRewind.setOnClickListener {
            playerDelegate.rewind()
            hideControls(3)
        }
        binding.seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?, progress: Int, fromUser: Boolean
            ) {
                if (fromUser) {
                    isSeeking = true
                    binding.tvTimeElapsed.text =
                        progress.toDouble().convertToMinutesAndSecondsFormat()
                    binding.tvTimeLeft.text =
                        (binding.seekbar.max - progress).toDouble()
                            .convertToMinutesAndSecondsFormat()
                    handlerSeekBar.removeCallbacks(runnableSeekBar)
                    runnableSeekBar = Runnable {
                        playerDelegate.seek(progress)
                    }
                    handlerSeekBar.postDelayed(runnableSeekBar, 500)
                    hideControls(5)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                /* Do nothing */
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                /* Do nothing */
            }
        })
    }


}