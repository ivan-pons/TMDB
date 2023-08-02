package com.ipons.tmdb.view.player.youbora

import android.app.Activity
import android.app.Application
import com.google.android.exoplayer2.ExoPlayer
import com.npaw.youbora.lib6.exoplayer2.Exoplayer2Adapter
import com.npaw.youbora.lib6.plugin.Options
import com.npaw.youbora.lib6.plugin.Plugin
import javax.inject.Inject

class YouboraManager @Inject constructor(
    application: Application
) {

    private val plugin: Plugin
    private val options = Options()

    init {
        options.contentIsLive = false
        plugin = Plugin(options, application)
        //YouboraLog.setDebugLevel(YouboraLog.Level.VERBOSE)
    }

    fun setAccount(accountCode: String, transactionCode: String) {
        options.accountCode = accountCode
        options.contentTransactionCode = transactionCode
        plugin.options = options
    }

    fun setActivity(activity: Activity) {
        plugin.activity = activity
    }

    fun setAdapter(player: ExoPlayer?) {
        plugin.adapter = player?.let { Exoplayer2Adapter(it) }
    }

    fun removeAdapter() {
        plugin.adapter = null
    }

    fun setCustomParams(user: String, videoTitle: String) {
        options.username = user
        options.contentTitle = videoTitle
//        options.program = BuildConfig.VERSION_NAME
        plugin.options = options
    }

    fun setSource(sourceUrl: String) {
        options.contentResource = sourceUrl
        plugin.options = options
    }

}