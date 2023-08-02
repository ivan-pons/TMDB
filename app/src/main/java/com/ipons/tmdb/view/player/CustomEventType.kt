package com.ipons.tmdb.view.player


sealed class CustomEventType {
    object Play : CustomEventType()
    object Seek : CustomEventType()
    object Pause : CustomEventType()
    object Error : CustomEventType()
    object Finish : CustomEventType()
    class TimeChanged(val currentTime: Int) : CustomEventType()
    class StartPlay(val duration: Int) : CustomEventType()
}