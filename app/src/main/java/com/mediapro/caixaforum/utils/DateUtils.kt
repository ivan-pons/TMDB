package com.mediapro.caixaforum.utils

object DateUtils {
    fun secondsToHoursAndMinutes(seconds: Int): String {
        val hours = String.format("%02d", seconds / 3600)
        val minutes = String.format("%02d", seconds % 3600 / 60)
        return if (seconds / 3600 > 0) {
            "$hours:$minutes MIN"
        } else minutes
    }
}