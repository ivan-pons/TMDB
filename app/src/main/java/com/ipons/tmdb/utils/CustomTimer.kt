package com.ipons.tmdb.utils

import android.os.CountDownTimer

class CustomTimer(
    val maxSeconds: Int,
    val intervalMs: Long = 50L,
    val onTickCallback: (Long) -> Unit,
    val onFinishCallback: () -> Unit
) {

    private var timer: CountDownTimer? = null

    fun start() {
        cancel()

        timer = object : CountDownTimer(maxSeconds * 1000L, intervalMs) {
            override fun onTick(p0: Long) {
                onTickCallback(p0)
            }

            override fun onFinish() {
                onFinishCallback()
            }
        }

        timer!!.start()
    }

    fun cancel() {
        timer?.let {
            it.cancel()
            timer = null
        }
    }
}
