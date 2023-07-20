package com.ipons.tmdb.utils

fun Double.convertToMinutesAndSecondsFormat(): String {
    val secondsToLong = this.toLong()
    return secondsToLong.toTime()
}

fun Long.toTime(): String {
    val hours = String.format("%02d", this / 3600)
    val minutes = String.format("%02d", this % 3600 / 60)
    val seconds = String.format("%02d", this % 60)
    return if (this / 3600 > 0) {
        "$hours:$minutes:$seconds"
    } else "$minutes:$seconds"
}